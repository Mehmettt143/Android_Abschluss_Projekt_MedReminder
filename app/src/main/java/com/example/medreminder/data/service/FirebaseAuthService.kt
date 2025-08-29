package com.example.medreminder.data.service

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.also

class FirebaseAuthService {

    // empfohlen: Dependency Injection mit Koin verwenden -> singleOf(::FirebaseAuthService)
    // alternativ: ein singleton aus dem FirebaseAuthService machen
    companion object {
        @Volatile
        private var instance: FirebaseAuthService? = null

        // wenn es schon eine Instanz gibt, dib diese zurück
        // ansonsten erstelle eine Instanz, speicher sie ab und gib diese zurück
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FirebaseAuthService().also { instance = it }
            }
    }


    private val TAG = "FirebaseAuthService"

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    // Zustand der Authentifizierung -> Ist grade jmnd eingeloggt?
    // StateFLow -> Kann für LiveUpdates collectet werden
    private val _authState = MutableStateFlow(firebaseAuth.currentUser)
    val authState: StateFlow<FirebaseUser?> = _authState

    // Schaut was sich ändert (bei der Authentifizierung)
    // Wenn sich was ändert bei FirebaseAuth, dann wird unser authState geupdatet
    private val authStateListener = FirebaseAuth.AuthStateListener { auth ->
        _authState.value = auth.currentUser
    }

    init {
        // unseren listener zu firebaseAuth hinzufügen, dadurch ist er aktiv
        firebaseAuth.addAuthStateListener(authStateListener)

        // Alternative: Listener in einem Schritt erstellen und "aktivieren"
//        firebaseAuth.addAuthStateListener { auth ->
//            _authState.value = auth.currentUser
//        }
    }


    fun register(email: String, password: String): Result<Unit> {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
            return Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "register: failed", e)
            return Result.failure(e)
        }
    }

    fun login(email: String, password: String) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
        } catch (e: Exception) {
            Log.e(TAG, "login: failed", e)
            // TODO mehr Fehlerbehandlung, dem User eine passende Meldung anzeigen

        }
    }

    fun logout() {
        try {
            firebaseAuth.signOut()
        } catch (e: Exception) {
            Log.e(TAG, "logout: failed", e)
        }
    }
}