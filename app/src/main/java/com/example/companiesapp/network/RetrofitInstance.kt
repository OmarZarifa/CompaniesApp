package com.example.companiesapp.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.GsonConverterFactory

// initializes Retrofit and provide the ApiService instance
object RetrofitInstance {
    private const val BASE_URL = "https://firebasestorage.googleapis.com/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // For scalar responses
            .addConverterFactory(ScalarsConverterFactory.create())
            // For JSON responses
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
