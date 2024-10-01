package com.example.companiesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.companiesapp.ui.screens.CompanyListScreen
import com.example.companiesapp.ui.theme.CompaniesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompaniesAppTheme {
                CompanyListScreen()
            }
        }
    }
}
