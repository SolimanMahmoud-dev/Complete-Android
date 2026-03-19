package com.example.bookly.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.databinding.BestSellerItemBinding
import com.example.bookly.databinding.HeaderHomeItemBinding
import com.example.bookly.models.Book

class HomeAdapter(
    private val onSearchClick: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var headerBooks: MutableList<Book> = mutableListOf()
    private var books: MutableList<Book> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER_VIEW_TYPE
        } else {
            BEST_SELLER_VIEW_TYPE
        }
    }

    fun updateHeaderBooks(newHeaderBooks: List<Book>) {
        headerBooks.clear()
        headerBooks.addAll(newHeaderBooks)
        notifyDataSetChanged()
    }

    fun updateBestSellerBooks(newBestSellerBooks: List<Book>) {
        books.clear()
        books.addAll(newBestSellerBooks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == HEADER_VIEW_TYPE) {
            HeaderViewHolder(
                HeaderHomeItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            BestSellerViewHolder(
                BestSellerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (holder is HeaderViewHolder) {
            holder.bind(headerBooks)
        } else if (holder is BestSellerViewHolder) {
            holder.bind(books[position - 1])
        }
    }

    override fun getItemCount(): Int = books.size + 1

    class BestSellerViewHolder(private val binding: BestSellerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.book = book
            binding.root.setOnClickListener {
                it.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(book))
            }
        }
    }

    inner class HeaderViewHolder(private val binding: HeaderHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(books: List<Book>) {
            binding.recyclerViewBook.adapter = HeaderAdapter(books)
            binding.btnSearch.setOnClickListener {
                onSearchClick()
            }
        }
    }

    companion object {
        const val HEADER_VIEW_TYPE = 1
        const val BEST_SELLER_VIEW_TYPE = 2
    }
}