package com.example.bookly.models.api

data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    val publishedDate: String,
    val industryIdentifiers: List<IndustryIdentifier>,
    val readingModes: ReadingModes,
    val pageCount: Int,
    val printType: String,
    val categories: List<String>?,
    val averageRating: Double?,
    val ratingsCount: Int?,
    val maturityRating: String,
    val allowAnonLogging: Boolean,
    val contentVersion: String,
    val panelizationSummary: PanelizationSummary,
    val imageLinks: ImageLinks?,
    val language: String,
    val previewLink: String?,
    val infoLink: String,
    val canonicalVolumeLink: String
)
