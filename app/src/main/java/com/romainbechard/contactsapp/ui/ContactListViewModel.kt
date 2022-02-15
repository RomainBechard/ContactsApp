package com.romainbechard.contactsapp.ui

import androidx.lifecycle.*
import com.romainbechard.contactsapp.data.ContactsRepository
import com.romainbechard.contactsapp.data.model.User
import com.romainbechard.contactsapp.data.tools.Result
import kotlinx.coroutines.launch
import timber.log.Timber

class ContactListViewModel : ViewModel() {

    private lateinit var repository: ContactsRepository

    private val _contacts = MutableLiveData<List<User>>()
    val contacts: LiveData<List<User>> = _contacts

    fun configure(repository: ContactsRepository) {
        this.repository = repository
        viewModelScope.launch {
            when(val response = repository.getContacts()){
                is Result.Success -> _contacts.postValue(response.data)
                is Result.Error -> Timber.d("An error occured while retrieving contacts")
            }
        }
    }
}