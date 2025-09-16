package com.example.medreminder.data.repository

import android.util.Log
import com.example.medreminder.data.remote.drugapi.DrugAPIService
import com.example.medreminder.data.remote.drugapi.model.DrugLabelResponse

class ApiDrugRepository(
    private val drugApi: DrugAPIService
) : IDrugRepository {

    private val TAG = "ApiDrugRepository"

    override suspend fun getDrugLabels(searchQuery: String, limit: Int): Result<DrugLabelResponse> {
        try {
            val response = drugApi.getDrugLabels(searchQuery, limit)
            return Result.success(response)
        } catch (e: Exception) {
            Log.e(TAG, "getDrugLabels: Error loading drug labels", e)
            return Result.failure(Exception("Fehler beim Laden der Medikamentenlabels"))
        }
    }
}