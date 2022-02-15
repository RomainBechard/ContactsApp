package com.romainbechard.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.romainbechard.contactsapp.data.model.User
import com.romainbechard.contactsapp.ui.ContactListViewModel
import com.romainbechard.contactsapp.ui.theme.ContactsAppTheme
import com.romainbechard.contactsapp.ui.theme.createStreamColors

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ContactListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = applicationContext as ContactsApplication
        viewModel.configure(application.repository)
        setContent {
            ContactsAppTheme(colors = createStreamColors()) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(createStreamColors().background)
                ) {
                    val contacts by viewModel.contacts.observeAsState()
                    SearchWidget()
                    contacts?.let { ContacsList(contacts = it) }
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
    user: User
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
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

@Composable
fun SearchWidget() {
    var text by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(32.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            modifier = Modifier
                .background(createStreamColors().primaryVariant)
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