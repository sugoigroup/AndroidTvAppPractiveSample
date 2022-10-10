package com.sugoigroup.myapplicationtv5

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.sugoigroup.myapplicationtv5.databinding.ActivityDetailsBinding
import com.sugoigroup.myapplicationtv5.databinding.ActivityMainBinding

/**
 * Details activity class that loads [VideoDetailsFragment] class.
 */
class DetailsActivity : FragmentActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.detailsFragment.id, VideoDetailsFragment())
                .commitNow()
        }
    }

    companion object {
        const val SHARED_ELEMENT_NAME = "hero"
        const val MOVIE = "Movie"
    }
}