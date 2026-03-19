package com.example.bookly.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.databinding.BestSellerItemBinding
import com.example.bookly.models.Book

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private val books: MutableList<Book> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.ViewHolder {
        return ViewHolder(
            BestSellerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun updateBooks(newBooks: List<Book>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size


    class ViewHolder(private val binding: BestSellerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book
            binding.root.setOnClickListener {
                it.findNavController()
                    .navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(book))
            }
        }
    }

}