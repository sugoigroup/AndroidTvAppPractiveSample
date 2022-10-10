package com.sugoigroup.myapplicationtv5

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.sugoigroup.myapplicationtv5.databinding.ActivityDetailsBinding

/** Loads [PlaybackVideoFragment]. */
class PlaybackActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, PlaybackVideoFragment())
                .commit()
        }
    }
}