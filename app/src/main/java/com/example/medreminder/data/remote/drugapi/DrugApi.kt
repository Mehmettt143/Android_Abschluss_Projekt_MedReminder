package com.example.medreminder.data.remote.drugapi

import com.example.medreminder.data.remote.drugapi.model.DrugLabelResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface DrugAPIService {

    @GET("drug/label.json")
    suspend fun getDrugLabels(
        @Query("search") searchQuery: String,
        @Query("limit") limit: Int = 3
    ): DrugLabelResponse
}

object DrugApi {
    const val BASE_URL = "https://api.fda.gov/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {

        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
    val retrofitService: DrugAPIService by lazy { retrofit.create(DrugAPIService::class.java) }

}


