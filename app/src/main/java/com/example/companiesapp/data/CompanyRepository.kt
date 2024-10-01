package com.example.companiesapp.data


import com.example.companiesapp.model.CompaniesResponse
import com.example.companiesapp.network.RetrofitInstance

// network call via (Retrofit) and returns the data to the ViewModel
class CompanyRepository {
    suspend fun getCompanies(): CompaniesResponse {
        return RetrofitInstance.api.getCompanies() // Fetch data from the ApiService (which uses Retrofit)
    }
}


