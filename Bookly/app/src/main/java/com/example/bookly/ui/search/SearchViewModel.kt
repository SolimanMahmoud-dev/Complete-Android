package com.example.bookly.ui.search

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

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val _searchResults = MutableLiveData<List<Book>>()
    val searchResults: LiveData<List<Book>> get() = _searchResults

    fun fetchSearchBooks(query: String) {
        viewModelScope.launch {
            try {

                val response = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getBooks("subject:$query", "free-ebooks")
                }

                _searchResults.value = bookMap(response.items ?: emptyList())

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}