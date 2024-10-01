package com.example.companiesapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.companiesapp.model.Company

@Composable
fun CompanyListScreen(viewModel: CompanyViewModel = viewModel()) {
    val companyList by viewModel.companyList.collectAsState()  // Observe company list

    if (companyList.isEmpty()) {
        // Show placeholder if no data is available
        Text(text = "No companies found!")
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Company List", style = MaterialTheme.typography.titleLarge) },
                    backgroundColor = MaterialTheme.colorScheme.primary
                )
            }
        ) { innerPadding ->
            // scrollable list
            LazyColumn(modifier = Modifier.padding(innerPadding)
                .padding(16.dp) ) {
                items(companyList) { company ->
                    CompanyCard(company = company)
                }
            }
        }
        }
    }

// Display the details of each company
@Composable
fun CompanyCard(company: Company) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(text = company.title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = company.city,
                    modifier = Modifier.padding(6.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Row to place the webpage icon and the clickable text for the webpage
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Webpage Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))

                // Clickable text for the webpage
                ClickableText(
                    text = AnnotatedString(company.webpage),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                    onClick = {
                        try {
                            // Try opening the webpage in the browser
                            val intent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(company.webpage.trim()))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Log.e(
                                "CompanyCard",
                                "Could not open URL: ${company.webpage}, error: ${e.message}"
                            )
                        }
                    }
                )
            }
            Image(
                painter = rememberImagePainter(data = company.image),
                contentDescription = "Company Image",
                modifier = Modifier.fillMaxWidth().height(150.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}


