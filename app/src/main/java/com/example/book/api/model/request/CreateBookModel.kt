package com.example.book.api.model.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateBookModel(
    @Json(name = "title")
    val title: String?,
    @Json(name = "summary")
    val summary: String?,
    @Json(name = "author")
    val author: String?,
    @Json(name = "imageUrl")
    val imageUrl: String?,
    @Json(name = "categoryId")
    val categoryId: Int?
)