package com.example.book.api.model.response.book


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResponse(
    @Json(name = "data")
    val `data`: List<DataBook>,
    @Json(name = "totalItems")
    val totalItems: Int?,
    @Json(name = "totalPages")
    val totalPages: Int?,
    @Json(name = "itemsPerPage")
    val itemsPerPage: Int?,
    @Json(name = "page")
    val page: Int?
)