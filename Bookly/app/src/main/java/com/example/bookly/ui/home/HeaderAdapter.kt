package com.example.bookly.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.databinding.BookHomeListItemBinding
import com.example.bookly.models.Book

class HeaderAdapter(
    private val books: List<Book>
) : RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {

    private var _binding: BookHomeListItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        _binding =
            BookHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size


    class ViewHolder(private val binding: BookHomeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book
            binding.root.setOnClickListener {
                it.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(book))
            }
        }
    }
}