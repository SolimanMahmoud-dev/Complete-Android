package com.example.bookly.ui.search

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bookly.databinding.FragmentSearchBinding
import kotlinx.coroutines.Runnable

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModels()

    private val searchAdapter = SearchAdapter()

    private var searchHandler = android.os.Handler(Looper.getMainLooper())
    private var searchRunnable: Runnable? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.recyclerViewSearch.adapter = searchAdapter

        binding.search.doOnTextChanged { text, _, _, _ ->
            searchRunnable?.let { searchHandler.removeCallbacks(it) }

            searchRunnable = Runnable {
                searchViewModel.fetchSearchBooks(text.toString())
            }

            searchHandler.postDelayed(searchRunnable!!, 1000)
        }

        searchViewModel.searchResults.observe(viewLifecycleOwner) {
            searchAdapter.updateBooks(it)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment()
    }
}