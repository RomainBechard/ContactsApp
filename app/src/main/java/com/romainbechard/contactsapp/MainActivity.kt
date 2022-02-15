package com.romainbechard.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.romainbechard.contactsapp.data.model.User
import com.romainbechard.contactsapp.ui.ContactListViewModel
import com.romainbechard.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ContactListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = applicationContext as ContactsApplication
        viewModel.configure(application.repository)
        setContent {
            ContactsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(Modifier.fillMaxSize()) {
                        val contacts by viewModel.contacts.observeAsState()
                        contacts?.let { ContacsList(contacts = it) }
                    }
                }
            }
        }
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
    user: User = User(
        "Romain",
        "Bechard",
        "data:image/jpeg;base64,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"
    )
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
        Spacer(modifier = Modifier.padding(start = 16.dp))
        Text(
            text = user.firstName + " " + user.lastName,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContactsAppTheme {
    }
}