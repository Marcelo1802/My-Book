package com.example.book.api.model.response.create_book


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "updatedAt")
    val updatedAt: String?
)