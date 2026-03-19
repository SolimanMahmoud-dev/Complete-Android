package com.example.bookly.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.bookly.R
import com.example.bookly.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeAdapter = HomeAdapter {
        binding.root.findNavController()
            .navigate(R.id.action_homeFragment_to_searchFragment)
    }

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerViewHome.adapter = homeAdapter

        homeViewModel.headerBooks.observe(viewLifecycleOwner) {
            homeAdapter.apply {
                updateHeaderBooks(it)
                binding.progressBar.visibility = View.GONE
                binding.recyclerViewHome.visibility = View.VISIBLE
            }
        }

        homeViewModel.bestSellerBooks.observe(viewLifecycleOwner) {
            homeAdapter.apply {
                updateBestSellerBooks(it)
                binding.progressBar.visibility = View.GONE
                binding.recyclerViewHome.visibility = View.VISIBLE
            }
        }

        if(homeViewModel.headerBooks.value == null){
            homeViewModel.fetchHeaderBooks("Programming")
            homeViewModel.fetchBestSellerBooks("Computer Science")
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}