package com.example.book.api.model.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginModel(
    @Json(name = "credential")
    val credential: String?,
    @Json(name = "password")
    val password: String?
)