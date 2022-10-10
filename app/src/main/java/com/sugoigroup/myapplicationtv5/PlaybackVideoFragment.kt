package com.sugoigroup.myapplicationtv5

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.Action
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.PlaybackControlsRow
import java.lang.Math.max
import java.lang.Math.min

/** Handles video playback with media controls. */
class PlaybackVideoFragment : VideoSupportFragment() {

    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val (title, content, _, videoUrl) = CardItemData(
            title = "타이틀2",
            content = "컨텐츠2",
            imageUrl = "https://m.media-amazon.com/images/M/MV5BMWUwOThjYTAtZWYyYy00YjllLTkxYjEtNTJmNTI5N2M1NjkxXkEyXkFqcGdeQXVyOTU0NjY1MDM@._V1_UX140_CR0,0,140,209_AL_.jpg",
            videoUrl = "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4"
        )

        val glueHost = VideoSupportFragmentGlueHost(this@PlaybackVideoFragment)
        val playerAdapter = MediaPlayerAdapter(activity)
        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)

        mTransportControlGlue = MyMediaPlayerGlue(context, playerAdapter)
        mTransportControlGlue.host = glueHost
        mTransportControlGlue.title = title
        mTransportControlGlue.subtitle = content
        mTransportControlGlue.isSeekEnabled = true
        mTransportControlGlue.playWhenPrepared()

        showControlsOverlay(true)




        playerAdapter.setDataSource(Uri.parse(videoUrl))
    }

    override fun onPause() {
        super.onPause()
        mTransportControlGlue.pause()
    }

    inner class MyMediaPlayerGlue(context: Context?, adapter: MediaPlayerAdapter):
        PlaybackTransportControlGlue<MediaPlayerAdapter>(context, adapter) {

        private val actionRewind = PlaybackControlsRow.RewindAction(context)
        private val actionFastForward = PlaybackControlsRow.FastForwardAction(context)
        private val actionClosedCaptions = PlaybackControlsRow.ClosedCaptioningAction(context)

        fun skipForward(millis: Long = 3000) =
            playerAdapter.seekTo(if (playerAdapter.mediaPlayer.duration > 0) {
                min(playerAdapter.duration, playerAdapter.currentPosition + millis)
            } else {
                playerAdapter.currentPosition + millis
            })

        fun skipBackward(millis: Long = 3000) =
            playerAdapter.seekTo(max(0, playerAdapter.currentPosition - millis))

        override fun onActionClicked(action: Action) = when(action) {
            actionRewind -> skipBackward()
            actionFastForward -> skipForward()
            else -> super.onActionClicked(action)
        }

        override fun onCreatePrimaryActions(adapter: ArrayObjectAdapter) {
            super.onCreatePrimaryActions(adapter)
            adapter.add(actionRewind)
            adapter.add(actionFastForward)
            adapter.add(actionClosedCaptions)
        }
    }
}