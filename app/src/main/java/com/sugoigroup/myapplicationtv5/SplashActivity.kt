package com.sugoigroup.myapplicationtv5

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.sugoigroup.myapplicationtv5.databinding.ActivitySplashBinding

class SplashActivity : FragmentActivity() {

    private lateinit var myBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(myBinding.root)

        val animationDuring = 3000L
        val alphaAnimation = ObjectAnimator.ofFloat(myBinding.imageView, View.ALPHA, 0f, 1f)


        alphaAnimation.addListener(object : AnimatorListenerAdapter() {

            val mediaPlayer = MediaPlayer()
            val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-17.mp3"

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)

                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

                try {
                    mediaPlayer.setDataSource(audioUrl)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                mediaPlayer.stop()
                mediaPlayer.release()
                finish()
                startActivity(Intent(baseContext, OnboardingActivity::class.java))
            }
        })
        alphaAnimation.setDuration(animationDuring).start()
    }
}