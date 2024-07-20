package com.example.book.api.model.response.register


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "updatedAt")
    val updatedAt: String?
)