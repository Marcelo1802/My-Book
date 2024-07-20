package com.example.book.api.model.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterModel(
    @Json(name = "name")
    val name: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "password")
    val password: String?,
    @Json(name = "confirmPassword")
    val confirmPassword: String?
)