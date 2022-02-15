package com.romainbechard.contactsapp.data

import com.romainbechard.contactsapp.BuildConfig
import com.romainbechard.contactsapp.data.model.GetContactsResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ContactsApi {

    @Headers(BuildConfig.API_HEADER)
    @GET("user")
    suspend fun getContacts(): GetContactsResponse
}