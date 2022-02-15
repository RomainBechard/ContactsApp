package com.romainbechard.contactsapp.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GetContactsResponse(
    @JsonProperty("data") val contacts: List<UserDTO>,
    @JsonProperty("total") val total: Int?,
    @JsonProperty("page") val page: Int?,
    @JsonProperty("limit") val limit: Int?
)
