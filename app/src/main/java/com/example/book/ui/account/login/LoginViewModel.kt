package com.example.book.ui.account.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book.R
import com.example.book.api.model.request.LoginModel
import com.example.book.api.model.response.book.BookResponse
import com.example.book.api.model.response.categories.CategoriesResponse
import com.example.book.api.model.response.login.LoginResponse
import com.example.book.repository.BookRepository
import com.example.book.utils.Preferences
import com.example.book.utils.ResponseParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(
    private val repository: BookRepository,
    private val preferences: Preferences
) : ViewModel() {

    val successLogin = MutableLiveData<LoginResponse>()
    val error = MutableLiveData<String>()

    fun createSession(token: LoginResponse) {
        preferences.createSession(token)
    }

    fun login(user: LoginModel) {
        val request = repository.login(user)
        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    successLogin.postValue(response.body())
                } else {
                    error.postValue(ResponseParser.parseError(response))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                error.postValue(t.message)
            }

        })

    }



}