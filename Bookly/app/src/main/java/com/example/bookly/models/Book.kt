package com.example.bookly.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val image: String,
    val name: String,
    val author: String,
    val price: Double,
    val rate: Double,
    val rateNumber: Int,
    val category: String,
    val preview: String
): Parcelable