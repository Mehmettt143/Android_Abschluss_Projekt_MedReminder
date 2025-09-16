package com.example.medreminder.di

import com.example.medreminder.data.remote.drugapi.DrugApi
import com.example.medreminder.data.repository.ApiDrugRepository
import com.example.medreminder.data.repository.IDrugRepository
import com.example.medreminder.ui.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        DrugApi.retrofitService
    }
    single<IDrugRepository> {
        ApiDrugRepository(
            get()
        )
    }
    viewModelOf(::HomeViewModel)


}


