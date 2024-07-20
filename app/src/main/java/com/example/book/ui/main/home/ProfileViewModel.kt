package com.example.book.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book.api.model.response.book.DataBook
import com.example.book.repository.BookRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: BookRepository) : ViewModel() {

    private val _totalItems = MutableLiveData<Int>()
    val totalItems: LiveData<Int> get() = _totalItems

    private val _books = MutableLiveData<List<DataBook>>()
    val books: LiveData<List<DataBook>> get() = _books

//    fun fetchBooks() {
//        viewModelScope.launch {
//            try {
//                val response = repository.getBooks()
//                _totalItems.postValue(response.totalItems ?: 0)
//                _books.postValue(response.data)
//            } catch (e: Exception) {
//                // Handle error
//                _totalItems.postValue(0)
//                _books.postValue(emptyList())
//            }
//        }
//    }
}
