package com.example.book.repository

import com.example.book.api.model.request.CreateBookModel
import com.example.book.api.model.request.LoginModel
import com.example.book.api.model.request.RegisterModel
import com.example.book.api.service.BookService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody


class BookRepository(
    private val bookService: BookService
) {

    fun createAccount(body: RegisterModel) = bookService.createAccount(body)
    fun login(user: LoginModel) = bookService.login(user)
    fun getBooks(
        search: String? = null,
        categoryId: Int? = null
    ) = bookService.getBooks(search, categoryId)
    fun createBook(book: CreateBookModel) = bookService.createBook(book)
    fun getBooksById(id: Int) = bookService.getBooksById(id)
    fun deleteBook(id: Int) = bookService.deleteBook(id)
    fun getCategories() = bookService.getCategories()
    fun uploadImage(image: ByteArray) =  bookService.uploadImage(byteToMultpart(image))

    fun byteToMultpart(image: ByteArray): MultipartBody.Part {
        val mediaType = "image/jpeg".toMediaTypeOrNull()
        val requestBody = image.toRequestBody(mediaType)
        val imagePart = MultipartBody.Part.createFormData("file", "file.jpg", requestBody)
        return imagePart
    }



}