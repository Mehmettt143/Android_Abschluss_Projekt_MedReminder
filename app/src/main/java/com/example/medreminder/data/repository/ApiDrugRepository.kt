package com.example.medreminder.data.repository

import android.util.Log
import com.example.medreminder.data.remote.drugapi.DrugAPIService
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import com.example.medreminder.data.remote.firebase.FirebaseAuthService
import com.example.medreminder.data.remote.firebase.model.Drug
import com.example.medreminder.data.remote.firebase.model.DrugReminder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await


class ApiDrugRepository(
    private val drugApi: DrugAPIService,
    private val authService: FirebaseAuthService,
    private val firestore: FirebaseFirestore
) : IDrugRepository {

    private val TAG = "ApiDrugRepository"


    /*
    *
    * Hier wird aus der Api MedikamentenInfos geholt also der query von Benutzer mit Limit wird aufgerufen
    * mapNotNull sobald id nicht vorhanden ist wird es als null erkannt und nicht in die liste hinzugefügt.
    * alle results werden in Displaydrug gespeichert wenn keine Internetverbindung da ist kommt man in die
    * zweite catch-block error loading result wird als failure geworfen ansonsten wenn alles ok ist
    * wird die drugs liste in die Result.success hinzugefügt und der Result wird returned.
    * */
    override suspend fun getDrugs(searchQuery: String, limit: Int): Result<List<DisplayDrug>> {
        return try {
            val response = drugApi.getDrugLabels(searchQuery, limit)
            val drugs = response.results.mapNotNull { result ->
                try {
                    DisplayDrug(
                        id = result.id,
                        brandName = result.openfda?.brandName?.firstOrNull() ?: "",
                        genericName = result.openfda?.genericName?.firstOrNull(),
                        manufacturerName = result.openfda?.manufacturerName?.firstOrNull(),
                        indicationsAndUsage = result.indicationsAndUsage?.firstOrNull(),
                        dosageAndAdministration = result.dosageAndAdministration?.firstOrNull(),
                        warnings = result.warnings?.firstOrNull(),
                        adverseReactions = result.adverseReactions?.firstOrNull()
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "getDrugs: Failed to map drug", e)
                    null
                }
            }
            Result.success(drugs)
        } catch (e: Exception) {
            Log.e(TAG, "getDrugs: Error loading drugs", e)
            Result.failure(Exception("Fehler beim Laden der Medikamente"))
        }
    }

    override suspend fun addFavorite(displayDrug: DisplayDrug): Result<Unit> {
        return try {
            authService.authState.value?.uid?.let { userId ->
                val firebaseDrug = Drug(
                    id = displayDrug.id,
                    brandName = displayDrug.brandName ?: "",
                    genericName = displayDrug.genericName ?: "",
                    manufacturerName = displayDrug.manufacturerName ?: "",
                    indicationsAndUsage = displayDrug.indicationsAndUsage ?: "",
                    dosageAndAdministration = displayDrug.dosageAndAdministration ?: "",
                    warnings = displayDrug.warnings ?: "",
                    adverseReactions = displayDrug.adverseReactions ?: ""
                )
                firestore
                    .collection("users")
                    .document(userId)
                    .collection("favorites")
                    .document(displayDrug.id)
                    .set(firebaseDrug)
                    .await()
                Result.success(Unit)
            } ?: Result.failure(Exception("User not logged in"))
        } catch (e: Exception) {
            Log.e(TAG, "addFavorite: failed", e)
            Result.failure(e)
        }
    }

    // Favorites wird durch die drugId parameter in der collection document gefunden und wird gelöscht
    override suspend fun removeFavorite(drugId: String): Result<Unit> {
        return try {
            authService.authState.value?.uid?.let { userId ->
                firestore
                    .collection("users")
                    .document(userId)
                    .collection("favorites")
                    .document(drugId)
                    .delete()
                    .await()
                Result.success(Unit)
            } ?: Result.failure(Exception("User not logged in"))
        } catch (e: Exception) {
            Log.e(TAG, "removeFavorite: failed", e)
            Result.failure(e)
        }
    }

    //hier wird geguckt wenn aktuelle Benutzerid da ist wird es in der document geguckt ob drugId da ist existiert
    override suspend fun isFavorite(drugId: String): Boolean {
        return try {
            authService.authState.value?.uid?.let { userId ->
                val doc = firestore
                    .collection("users")
                    .document(userId)
                    .collection("favorites")
                    .document(drugId)
                    .get()
                    .await()
                doc.exists()
            } == true
        } catch (e: Exception) {
            Log.e(TAG, "isFavorite: failed", e)
            false
        }
    }

    //hier wird der Username geholt als String in Result returned
    override suspend fun getUsername(): Result<String> {
        return try {
            authService.authState.value?.uid?.let { userId ->
                val document = firestore
                    .collection("users")
                    .document(userId)
                    .get()
                    .await()
                val username = document.getString("username")
                if (username != null) {
                    Result.success(username)
                } else {
                    Result.failure(Exception("Username not found"))
                }
            } ?: Result.failure(Exception("Failure: UserId not found"))
        } catch (e: Exception) {
            Log.e(TAG, "getUsername: failed", e)
            Result.failure(Exception("Failure:$e"))
        }
    }

    //
    //alle Favoriten Drugs werden geholt durch user Id favoriten collection wird in Drug Klasse umgewandelt
    //und in die druglist übertragen dann alls Result list drug returned

    override suspend fun getAllFavorites(): Result<List<Drug>> {
        val currentUser = authService.authState.value
        return if (currentUser != null) {
            try {
                val drugList = firestore
                    .collection("users")
                    .document(currentUser.uid)
                    .collection("favorites")
                    .get()
                    .await()
                    .toObjects(Drug::class.java)
                Result.success(drugList)
            } catch (e: FirebaseFirestoreException) {
                val errorMessage = when (e.code) {
                    FirebaseFirestoreException.Code.PERMISSION_DENIED -> "Permission denied"
                    FirebaseFirestoreException.Code.UNAVAILABLE -> "Network error"
                    else -> "Failed to fetch favorites"
                }
                Result.failure(Exception(errorMessage, e))
            }
        } else {
            Result.failure(Exception("User not logged in"))
        }
    }

    //hier wird ein DrugReminder in firestore gespeichert
    override suspend fun addDrug(drugReminder: DrugReminder): Result<Unit> {
        return try {
            authService.authState.value?.uid?.let { userId ->
                firestore
                    .collection("users")
                    .document(userId)
                    .collection("my_drugs")
                    .document(drugReminder.id)
                    .set(drugReminder)
                    .await()
                Result.success(Unit)
            } ?: Result.failure(Exception("User not logged in"))
        } catch (e: Exception) {
            Log.e(TAG, "addDrug: failed", e)
            Result.failure(e)
        }
    }

    //durch drugid wird auf die document my_drugs zugegriffen und gelöscht
    override suspend fun removeDrug(drugId: String): Result<Unit> {
        return try {
            authService.authState.value?.uid?.let { userId ->
                firestore
                    .collection("users")
                    .document(userId)
                    .collection("my_drugs")
                    .document(drugId)
                    .delete()
                    .await()
                Result.success(Unit)
            } ?: Result.failure(Exception("User not logged in"))
        } catch (e: Exception) {
            Log.e(TAG, "removeDrug: failed", e)
            Result.failure(e)
        }
    }

    //alle DrugReminder wird als Liste geholt
    override suspend fun getAllMyDrugs(): Result<List<DrugReminder>> {
        return try {
            authService.authState.value?.uid?.let { userId ->
                val drugList = firestore
                    .collection("users")
                    .document(userId)
                    .collection("my_drugs")
                    .get()
                    .await()
                    .toObjects(DrugReminder::class.java)
                Result.success(drugList)
            } ?: Result.failure(Exception("User not logged in"))
        } catch (e: Exception) {
            Log.e(TAG, "getAllMyDrugs: failed", e)
            Result.failure(Exception("MyDrugs failed"))
        }
    }
}
