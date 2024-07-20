package com.example.book.utils


import android.content.Context
import com.example.book.utils.Validation.isEmailValid


fun String.isValidEmail(): Boolean {
    return isEmailValid(this)
}

fun Context.getPreferenceData(): Preferences {
    return Preferences(this)
}



