package com.romainbechard.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.romainbechard.contactsapp.ui.ContactListViewModel
import com.romainbechard.contactsapp.ui.screens.ContactsScreen
import com.romainbechard.contactsapp.ui.screens.DetailsScreen
import com.romainbechard.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ContactListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = applicationContext as ContactsApplication
        viewModel.configure(application.repository)
        setContent {
            ContactsAppTheme {
                ContactsListApp(contactsViewModel = viewModel)
            }
        }
    }
}

@Composable
fun ContactsListApp(contactsViewModel: ContactListViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "contacts") {
        composable(route = "contacts") {
            ContactsScreen(navController, contactsViewModel)
        }
        composable(route = "details") {
            DetailsScreen(navController)
        }
    }
}