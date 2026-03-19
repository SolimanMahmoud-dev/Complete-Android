package com.example.bookly.ui.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.databinding.BookDetailsListItemBinding
import com.example.bookly.models.Book


class DetailsAdapter() : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    private val books: MutableList<Book> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsAdapter.ViewHolder {
        return ViewHolder(
            BookDetailsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailsAdapter.ViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    fun updateSimilarBooks(newSimilarBooks: List<Book>) {
        books.clear()
        books.addAll(newSimilarBooks)
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: BookDetailsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.book = book
        }
    }
}