package com.example.book.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book.R
import com.example.book.api.model.request.LoginModel
import com.example.book.api.model.request.RegisterModel
import com.example.book.api.model.response.book.BookResponse
import com.example.book.api.model.response.categories.CategoriesResponse
import com.example.book.api.model.response.login.LoginResponse
import com.example.book.api.model.response.register.RegisterResponse
import com.example.book.repository.BookRepository
import com.example.book.utils.Preferences
import com.example.book.utils.ResponseParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterViewModel(
    private val repository: BookRepository
) : ViewModel() {

    val successRegister = MutableLiveData<RegisterResponse>()
    val error = MutableLiveData<String>()


    fun createAccount(user: RegisterModel) {
        val request = repository.createAccount(user)
        request.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    successRegister.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }



}