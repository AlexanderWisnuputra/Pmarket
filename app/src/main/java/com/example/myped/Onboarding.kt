package com.example.myped

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myped.databinding.ActivityMainBinding
import com.example.myped.databinding.FragmentOnboardingBinding
import com.example.myped.databinding.FragmentSplashBinding

class Onboarding : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentOnboardingBinding.inflate(layoutInflater)

        val viewPager: ViewPager2 = binding.viewPager
        val fragments = listOf(Onboarding()) // Replace with your fragment instances
        val adapter = fragmentManager?.let { ViewPagerAdapter(fragments, it, lifecycle) }
        viewPager.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding, container, false)

    }

    // ViewPagerAdapter.kt
    class ViewPagerAdapter(private val fragments: List<Fragment>, fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}



