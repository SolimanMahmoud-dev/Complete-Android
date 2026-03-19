package com.example.bookly.models.api

data class ApiResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Item>?
)