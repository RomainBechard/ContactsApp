package com.romainbechard.contactsapp.data

import com.romainbechard.contactsapp.data.model.GetContactsResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ContactsApi {

    @Headers("app-id:620a7cbfa003942eecff9551")
    @GET("user")
    suspend fun getContacts(): GetContactsResponse
}