package com.example.myped

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myped.databinding.FragmentOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class Onboarding : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        binding.next.setOnClickListener(View.OnClickListener { navigateToNextItem() })
        binding.skip.setOnClickListener(){
            findNavController().navigate(R.id.action_onboarding_to_login)
        }
        binding.imageView2.setOnClickListener(){
            findNavController().navigate(R.id.action_onboarding_to_register)
        }



        viewPager = binding.viewPager
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position != 2) {
                    binding.next.visibility = View.VISIBLE
                } else {
                    binding.next.visibility = View.GONE
                }
            }
        })
        val drawableList = listOf(
            R.drawable.onboarding,
            R.drawable.onboarding1,
            R.drawable.onboarding2
            // Add more drawables as needed
        )
        val adapter = ViewPagerAdapter(drawableList)


        viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.customView = layoutInflater.inflate(R.layout.dot_image, null)
        }.attach()
        return binding.root
    }


    private fun navigateToNextItem() {
        val currentItem = viewPager.currentItem
        val lastItem = viewPager.adapter?.itemCount?.minus(1)
        if (currentItem < lastItem!!) {
            viewPager.setCurrentItem(currentItem + 1, true)
        }
    }
}
