package com.example.book.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book.R
import com.example.book.api.model.response.book.BookResponse
import com.example.book.api.model.response.categories.CategoriesResponse
import com.example.book.api.model.response.categories.CategoriesResponseItem
import com.example.book.repository.BookRepository
import com.example.book.utils.ResponseParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel(
    private val repository: BookRepository,
) : ViewModel() {



    val successGetCategories = MutableLiveData<List<CategoriesResponseItem>>()
    val successGetBook = MutableLiveData<BookResponse>()
    val error = MutableLiveData<String>()

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

    fun getMyBooks(
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
                    successGetBook.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }

}