package com.example.companiesapp.network

import com.example.companiesapp.model.CompaniesResponse
import retrofit2.http.GET

// Retrofit interface
interface ApiService {
    @GET("v0/b/companiesapp-74748.appspot.com/o/companies_info.json?alt=media&token=80b361c2-975d-4c34-91f9-dc8a5a50a889")
    suspend fun getCompanies(): CompaniesResponse
}
