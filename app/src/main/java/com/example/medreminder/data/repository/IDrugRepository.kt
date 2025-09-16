package com.example.medreminder.data.repository

import com.example.medreminder.data.remote.drugapi.model.DrugLabelResponse

interface IDrugRepository {

        suspend fun getDrugLabels(searchQuery: String, limit: Int = 3): Result<DrugLabelResponse>
    }
