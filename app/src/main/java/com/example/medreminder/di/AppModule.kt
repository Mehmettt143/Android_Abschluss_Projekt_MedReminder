package com.example.medreminder.di


import com.example.medreminder.data.remote.drugapi.DrugApi
import com.example.medreminder.data.remote.drugapi.DrugAPIService
import com.example.medreminder.data.remote.firebase.FirebaseAuthService
import com.example.medreminder.data.repository.ApiDrugRepository
import com.example.medreminder.data.repository.IDrugRepository
import com.example.medreminder.ui.viewmodel.AuthViewModel
import com.example.medreminder.ui.viewmodel.DetailViewModel
import com.example.medreminder.ui.viewmodel.DrugAddViewModel
import com.example.medreminder.ui.viewmodel.FavoriteViewModel
import com.example.medreminder.ui.viewmodel.HomeViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<DrugAPIService> {
        DrugApi.retrofitService
    }
    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }
    single<IDrugRepository> {
        ApiDrugRepository(get(), get(), get()) // drugApi, authService, firestore
    }
    singleOf(::FirebaseAuthService)
    singleOf(::ApiDrugRepository)
    viewModelOf(::HomeViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::FavoriteViewModel)
    viewModelOf(::DrugAddViewModel)
}