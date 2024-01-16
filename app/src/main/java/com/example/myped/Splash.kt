package com.example.myped

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myped.databinding.FragmentSplashBinding


class Splash : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {

            animateView(splash, "FADE_ANIMATION", 0f, 1f, 800L)
            animateView(splash, "ALPHA_ANIMATION", 0f, 1f, 800L)
            animateView(yellowSplash, "TRANSLATION_ANIMATION", 0f, -70f, 1000L)
            animateView(yellowSplash, "TRANSLATION_ANIMATION2", 0f, -70f, 1000L)
            animateView(yellowSplash, "translationY", 0f, 70f, 1000L)
            animateView(yellowSplash, "ROTATE_ANIMATION", 0f, -20f, 1000L)
            animateView(redSplash, "TRANSLATION_ANIMATION", 0f, 130f, 1000L)
            animateView(redSplash, "TRANSLATION_ANIMATION2", 0f, -60f, 1000L)
            animateView(redSplash, "ROTATE_ANIMATION", 0f, 20f, 1000L)
            animateView(greenSplash, "margin_anim", 0F, 400f, 1000L)
            val ani = ObjectAnimator.ofFloat(view, "translationY", -100f)
            ani.duration = 1000
            ani.start()
            Handler(Looper.getMainLooper()).postDelayed({ findNavController().navigate(R.id.action_splash_to_onboarding)
            }, 2000)


        }
    }

    private fun animateView(
        view: View, animationType: String,
        startValue: Float, endValue: Float, timeDuration: Long = 1500
    ) {


        val animator = ValueAnimator.ofFloat(startValue, endValue)
        animator.duration = timeDuration
        animator.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            when (animationType) {
                "ROTATE_ANIMATION" -> view.rotation = animatedValue
                "TRANSLATION_ANIMATION" -> view.translationX = animatedValue
                "TRANSLATION_ANIMATION2" -> view.translationY = animatedValue
                "ALPHA_ANIMATION"-> view.scaleX = animatedValue
                "FADE_ANIMATION" -> view.alpha = animatedValue
                "margin_anim" -> {
                    val lp = view.layoutParams as ConstraintLayout.LayoutParams
                    lp.setMargins(0, 0, 0, animatedValue.toInt())
                    view.layoutParams = lp
                    view.requestLayout()
                }
            }
        }
        animator.start()
    }

}