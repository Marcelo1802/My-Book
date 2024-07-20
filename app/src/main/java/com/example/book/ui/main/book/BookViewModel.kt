package com.example.book.ui.main.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book.api.model.request.CreateBookModel
import com.example.book.api.model.response.book.BookResponse
import com.example.book.api.model.response.book_by_id.BookByIdResponse
import com.example.book.api.model.response.categories.CategoriesResponseItem
import com.example.book.api.model.response.create_book.CreateBookResponse
import com.example.book.api.model.response.upload.UploadResponse
import com.example.book.repository.BookRepository
import com.example.book.utils.ResponseParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookViewModel(
    private val repository: BookRepository,
) : ViewModel() {


    val successGetBook = MutableLiveData<BookByIdResponse>()
    val successGetBookList = MutableLiveData<BookResponse>()
    val successGetCategories = MutableLiveData<List<CategoriesResponseItem>>()
    val uploadSuccess = MutableLiveData<UploadResponse>()
    val successGetBookDelete = MutableLiveData<Void>()
    val successNewBook = MutableLiveData<CreateBookResponse>()
    val error = MutableLiveData<String>()

    fun getMyBook(
        id: Int,
        ) {
        val request = repository.getBooksById(
            id
        )
        request.enqueue(object : Callback<BookByIdResponse> {
            override fun onResponse(call: Call<BookByIdResponse>, response: Response<BookByIdResponse>) {
                if (response.isSuccessful) {
                    successGetBook.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<BookByIdResponse>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }

    fun deleteBook(
        id: Int,
    ) {
        val request = repository.deleteBook(
            id
        )
        request.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    successGetBookDelete.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }

    fun getMyBooksList(
        search: String? = null,
        categoryId: Int? = null
    ) {
        val request = repository.getBooks(
            search = search,
            categoryId = categoryId
        )
        request.enqueue(object : Callback<BookResponse> {
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                if (response.isSuccessful) {
                    successGetBookList.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }

    fun createBook(bookModel: CreateBookModel) {
        val request = repository.createBook(bookModel)
        request.enqueue(object : Callback<CreateBookResponse> {
            override fun onResponse(call: Call<CreateBookResponse>, response: Response<CreateBookResponse>) {
                if (response.isSuccessful) {
                    successNewBook.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<CreateBookResponse>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }

    fun getCategories() {
        val request = repository.getCategories()
        request.enqueue(object : Callback<List<CategoriesResponseItem>> {
            override fun onResponse(call: Call<List<CategoriesResponseItem>>, response: Response<List<CategoriesResponseItem>>) {
                if (response.isSuccessful) {
                    successGetCategories.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<List<CategoriesResponseItem>>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }

    fun uploadImage(image: ByteArray) {
        repository.uploadImage(image).enqueue(object : Callback<UploadResponse> {
            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                if(response.isSuccessful){
                    uploadSuccess.postValue(response.body())
                }else{
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                error.postValue(t.message)
            }

        })
    }


}