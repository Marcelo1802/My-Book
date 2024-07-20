package com.example.book.ui.init.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.book.R
import com.example.book.databinding.FragmentStepOnboardingBinding


class StepOnboardingFragment(val step: Int) : Fragment() {
    constructor() : this(0)

    private var _binding: FragmentStepOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

    }

    private fun setupUI() {
        when (step) {
            STEP_ONE -> stepOneOnboarding()
            STEP_TWO -> stepTwoOnboarding()
        }
    }

    private fun stepOneOnboarding() {
        binding.apply {
            descriptionStep.text = getString(R.string.step_one_onboarding)
            imageStep.load(R.drawable.onbording_s1)
        }
    }

    private fun stepTwoOnboarding() {
        binding.apply {
            descriptionStep.text = getString(R.string.step_two_onboarding)
            imageStep.load(R.drawable.onbording_s2)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        private const val STEP_ONE = 0
        private const val STEP_TWO = 1
        private const val STEP_THREE = 2
    }


}