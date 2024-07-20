package com.example.book.api.model.response.book


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataBook(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String?,
    @Json(name = "summary")
    val summary: String?,
    @Json(name = "author")
    val author: String?,
    @Json(name = "imageUrl")
    val imageUrl: String?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "updatedAt")
    val updatedAt: String?,
    @Json(name = "category")
    val category: Category?
)