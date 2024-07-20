package com.example.book.api.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "erro")
    val erro: Boolean?,
    @Json(name = "error")
    val error: String?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "message")
    val code: Int?,
    @Json(name = "errors")
    val errors: List<Error>?
) {
    @JsonClass(generateAdapter = true)
    data class Error(
        @Json(name = "code")
        val code: String?,
        @Json(name = "minimum")
        val minimum: Int?,
        @Json(name = "type")
        val type: String?,
        @Json(name = "inclusive")
        val inclusive: Boolean?,
        @Json(name = "exact")
        val exact: Boolean?,
        @Json(name = "message")
        val message: String?,
        @Json(name = "path")
        val path: List<String>?,
        @Json(name = "error")
        val error: String?
    )


}