package com.example.bookly.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.bookly.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val detailsViewModel: DetailsViewModel by viewModels()

    private val detailsAdapter = DetailsAdapter()
    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.book = args.book
        binding.recyclerViewDetails.adapter = detailsAdapter
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerViewDetails.visibility = View.GONE

        detailsViewModel.similarBooks.observe(viewLifecycleOwner) {
            detailsAdapter.apply {
                updateSimilarBooks(it)
                binding.progressBar.visibility = View.GONE
                binding.recyclerViewDetails.visibility = View.VISIBLE
            }
        }

        detailsViewModel.fetchSimilarBooks(args.book.category)

        binding.preview.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args.book.preview)))
        }

        binding.exit.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailsFragment()
    }
}