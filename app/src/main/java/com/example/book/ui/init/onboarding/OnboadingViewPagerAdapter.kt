package com.example.book.ui.init.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class OnboadingViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val step: Int) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return step
    }

    override fun createFragment(position: Int): Fragment {
        return StepOnboardingFragment(position)
    }
}