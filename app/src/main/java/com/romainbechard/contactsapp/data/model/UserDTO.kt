package com.romainbechard.contactsapp.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDTO(
    @JsonProperty("id") val id: String,
    @JsonProperty("title") val title: String, //Mr, Ms etc
    @JsonProperty("firstName") val firstName: String,
    @JsonProperty("lastName") val lastName: String,
    @JsonProperty("picture") val pictureUrl: String
)
