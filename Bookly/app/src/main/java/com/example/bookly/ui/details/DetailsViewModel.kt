package com.example.bookly.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookly.models.Book
import com.example.bookly.network.ApiClient
import com.example.bookly.utils.bookMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val _similarBooks = MutableLiveData<List<Book>>()
    val similarBooks: LiveData<List<Book>> get() = _similarBooks

    fun fetchSimilarBooks(query: String) {
        viewModelScope.launch {
            try {

                val response = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getBooks("subject:$query", "free-ebooks")
                }

                _similarBooks.value = bookMap(response.items ?: emptyList())

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}