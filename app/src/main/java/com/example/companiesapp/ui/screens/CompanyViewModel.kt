package com.example.companiesapp.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.companiesapp.data.CompanyRepository
import com.example.companiesapp.model.Company
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//  Preparing and managing the data for the CompanyListScreen
class CompanyViewModel : ViewModel() {

    private val repository = CompanyRepository() // Request/fetch data from the CompanyRepository
    private val _companyList = MutableStateFlow<List<Company>>(emptyList())

    val companyList: StateFlow<List<Company>> = _companyList // Update the company List that Ui observes

    init {
        getCompanies()
    }

    private fun getCompanies() {
        viewModelScope.launch {
            try {
                val response = repository.getCompanies()

                _companyList.value = response.companies  // Access the "companies" list

            } catch (e: Exception) {
                Log.e("CompanyViewModel", "Error fetching companies", e)
            }
        }
    }
}

