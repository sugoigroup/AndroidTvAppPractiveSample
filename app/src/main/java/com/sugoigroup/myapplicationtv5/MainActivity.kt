package com.sugoigroup.myapplicationtv5

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.sugoigroup.myapplicationtv5.databinding.ActivityMainBinding
import com.sugoigroup.myapplicationtv5.databinding.ActivitySplashBinding

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(binding.mainBrowseFragment.id, MainFragment())
                .commitNow()
        }
    }
}