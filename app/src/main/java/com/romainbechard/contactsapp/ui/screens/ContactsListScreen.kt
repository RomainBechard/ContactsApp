package com.romainbechard.contactsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.romainbechard.contactsapp.R
import com.romainbechard.contactsapp.data.model.User
import com.romainbechard.contactsapp.ui.ContactListViewModel

@Composable
fun ContactsScreen(
    navController: NavController,
    viewModel: ContactListViewModel
) {
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        val contacts by viewModel.contacts.observeAsState()
        SearchWidget()
        contacts?.let { ContacsList(contacts = it) }
    }
}

@Composable
fun ContacsList(contacts: List<User>) {
    LazyColumn {
        items(contacts) { contact ->
            ContactCell(user = contact)
        }
    }
}

@Composable
fun ContactCell(
    user: User
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = user.photoUrl,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.padding(start = 24.dp))
        Text(
            text = user.firstName + " " + user.lastName,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun SearchWidget() {
    var text by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(32.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            modifier = Modifier
                .wrapContentHeight(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Image(
                    painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "Search icon"
                )
            },
            shape = RoundedCornerShape(32.dp)
        )
    }
}