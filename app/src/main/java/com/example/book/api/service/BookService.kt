package com.example.book.api.service


import com.example.book.api.model.request.CreateBookModel
import com.example.book.api.model.request.LoginModel
import com.example.book.api.model.request.RegisterModel
import com.example.book.api.model.response.book.BookResponse
import com.example.book.api.model.response.book_by_id.BookByIdResponse
import com.example.book.api.model.response.categories.CategoriesResponse
import com.example.book.api.model.response.categories.CategoriesResponseItem
import com.example.book.api.model.response.create_book.CreateBookResponse
import com.example.book.api.model.response.login.LoginResponse
import com.example.book.api.model.response.register.RegisterResponse
import com.example.book.api.model.response.upload.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {


    @POST("users")
    fun createAccount(
        @Body user: RegisterModel
    ): Call<RegisterResponse>

    @POST("auth/login")
    fun login(
        @Body user: LoginModel
    ): Call<LoginResponse>

    @GET("books")
    fun getBooks(
        @Query("search") search: String? = null,
        @Query("categoryId") categoryId: Int? = null
    ): Call<BookResponse>

    @POST("books")
    fun createBook(
        @Body book: CreateBookModel
    ): Call<CreateBookResponse>

    @GET("books/{id}")
    fun getBooksById(
        @Path("id") id: Int,
    ): Call<BookByIdResponse>

    @DELETE("books/{id}")
    fun deleteBook(
        @Path("id") id: Int,
    ): Call<Void>

    @GET("categories")
    fun getCategories(): Call<List<CategoriesResponseItem>>

    @Multipart
    @POST("upload-file")
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Call<UploadResponse>


}
