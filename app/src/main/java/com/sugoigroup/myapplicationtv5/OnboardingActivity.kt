package com.sugoigroup.myapplicationtv5

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.sugoigroup.myapplicationtv5.databinding.ActivityOnboardingBinding
import com.sugoigroup.myapplicationtv5.databinding.ActivitySplashBinding

class OnboardingActivity : FragmentActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}