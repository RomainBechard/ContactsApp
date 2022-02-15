package com.romainbechard.contactsapp.data

import com.romainbechard.contactsapp.data.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.romainbechard.contactsapp.data.tools.Result

class ContactsRepository(
    private val api: ContactsApi,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getContacts(): Result<List<User>> = withContext(dispatcher) {
        return@withContext try {
            val response = api.getContacts()
            val contacts = mutableListOf<User>()
            response.contacts.forEach { contacts.add(User(it.firstName, it.lastName, it.pictureUrl)) }
            Result.Success(contacts)
        } catch(e: Exception) {
            Result.Error(e)
        }
    }
}