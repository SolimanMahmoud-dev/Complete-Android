package com.example.bookly.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.bookly.models.Book
import com.example.bookly.models.api.Item

@BindingAdapter("imageRes")
fun setImageRes(imageView: ImageView, resId: Int) {
    imageView.setImageDrawable(
        ContextCompat.getDrawable(
            imageView.context,
            resId
        )
    )
}

@BindingAdapter("imageNetwork")
fun setImageNetwork(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url.replace("http", "https"))
        .into(imageView)
}

fun bookMap(items: List<Item>): List<Book> {
    return items.mapNotNull { item ->
        item.volumeInfo.run {
            val image = imageLinks?.thumbnail
            val name = title
            val author = authors?.firstOrNull()
            val rate = averageRating ?: 0.0
            val rateNumber = ratingsCount ?: 0
            val category = categories?.firstOrNull()
            val preview = previewLink

            takeIf {
                image != null &&
                        name != null &&
                        author != null &&
                        category != null &&
                        preview != null
            }?.let {
                Book(image!!, name!!, author!!, 0.0, rate, rateNumber, category!!, preview!!)
            }
        }

    }
}