package com.bldevelopers.ankur.activitys

import androidx.lifecycle.ViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController

class VideoViewModel:ViewModel() {


    fun videoPlayer(videoId:String?,youtubePlayerView:YouTubePlayerView) {

        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // using pre-made custom ui
                val defaultPlayerUiController =
                    DefaultPlayerUiController(youtubePlayerView, youTubePlayer)
                youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)

                val videoId = "$videoId"
                youTubePlayer.loadVideo(videoId, 0f)
            }
        }


        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        youtubePlayerView.initialize(listener, options)


    }


}