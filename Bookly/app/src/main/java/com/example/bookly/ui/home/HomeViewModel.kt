package com.example.bookly.ui.home

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

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _headerBooks = MutableLiveData<List<Book>>()
    val headerBooks: LiveData<List<Book>> get() = _headerBooks

    private val _bestSellerBooks = MutableLiveData<List<Book>>()
    val bestSellerBooks: LiveData<List<Book>> get() = _bestSellerBooks

    fun fetchHeaderBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getBooks("subject:$query", "free-ebooks")
                }

                _headerBooks.value = bookMap(response.items ?: emptyList())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
//        ApiClient.apiService.getBooks("subject:$query", "free-ebooks")
//            .enqueue(object : Callback<ApiResponse> {
//                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                    if (response.isSuccessful) {
//                        _headerBooks.value = bookMap(response.body()?.items ?: emptyList())
//                    }
//                }
//
//                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//
//                }
//            })
    }

    fun fetchBestSellerBooks(query: String) {
        viewModelScope.launch {
            try {

                val response = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getNewestBooks("subject:$query", "free-ebooks", "newest")
                }

                _bestSellerBooks.value = bookMap(response.items ?: emptyList())

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
//        ApiClient.apiService.getNewestBooks("subject:$query", "free-ebooks", "newest").enqueue(
//            object : Callback<ApiResponse> {
//                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                    if (response.isSuccessful) {
//                        _bestSellerBooks.value = bookMap(response.body()?.items ?: emptyList())
//                    }
//                }
//
//                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//
//                }
//            }
//        )

}