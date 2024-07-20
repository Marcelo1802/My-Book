package com.example.book.utils;

import android.content.Context
import android.content.SharedPreferences
import com.example.book.api.model.response.login.LoginResponse


class Preferences {

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var context: Context
    var MODE_PRIVATE : Int = 0

    constructor(context: Context) {
        this.context = context
        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
        editor = preferences.edit()

    }

    companion object {
        val PREFERENCES_NAME = "Preferences"
        val IS_LOGGIN = "isLoggin"
        val KEY_TOKEN = "token"
    }

    fun checkLogin() : Boolean {
        return preferences.getBoolean(IS_LOGGIN, false)
    }

    fun getToken() : String {
        return preferences.getString(KEY_TOKEN, null).toString()
    }

    fun createSession(response: LoginResponse){
        editor.putBoolean(IS_LOGGIN, true)
        editor.putString(KEY_TOKEN, response.token)
        editor.commit()
        editor.apply()
    }



}