package com.example.book.ui.init.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.example.book.databinding.FragmentOnboardingBinding
import com.example.book.ui.account.AccountActivity


class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: OnboadingViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPage()
        setupClick()
    }

    private fun setupClick() {
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(requireContext(), AccountActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onStop() {
        super.onStop()
        setupViewPage()

    }

    private fun setupViewPage() {
        adapter = OnboadingViewPagerAdapter(childFragmentManager, lifecycle, STEPS_QUANTITY)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setupButtonView(position)
            }
        })
    }

    fun setupButtonView(positon: Int) {
        if (positon == LAST_STEP) {
            binding.btnContinue.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        private const val LAST_STEP = 1
        private const val STEPS_QUANTITY = 2
    }

}