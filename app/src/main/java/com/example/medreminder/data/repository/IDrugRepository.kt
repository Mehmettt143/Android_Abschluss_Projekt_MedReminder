package com.example.medreminder.data.repository

import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import com.example.medreminder.data.remote.firebase.model.Drug
import com.example.medreminder.data.remote.firebase.model.DrugReminder


interface IDrugRepository {

    //addDrug
    suspend fun addDrug(drugReminder: DrugReminder): Result<Unit>
    suspend fun removeDrug(drugId: String): Result<Unit>
    suspend fun getAllMyDrugs(): Result<List<DrugReminder>>

    suspend fun getDrugs(query: String, limit: Int): Result<List<DisplayDrug>>
    suspend fun getAllFavorites(): Result<List<Drug>>
    suspend fun addFavorite(drug: DisplayDrug): Result<Unit>
    suspend fun removeFavorite(drugId: String): Result<Unit>
    suspend fun isFavorite(drugId: String): Boolean
    suspend fun getUsername(): Result<String>


}

