package com.example.companiesapp.model

// Represent the structure of the data fetched from the network
data class Company(
    val id: Int,
    val title: String,
    val city: String,
    val webpage: String,
    val image: String,
)
