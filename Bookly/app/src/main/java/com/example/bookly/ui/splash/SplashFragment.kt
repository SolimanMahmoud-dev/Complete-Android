package com.example.bookly.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bookly.R
import com.example.bookly.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        binding.splashTitle.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.move)
        )

        Handler(Looper.getMainLooper()).postDelayed({
            binding.root.findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }, 2000)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}
