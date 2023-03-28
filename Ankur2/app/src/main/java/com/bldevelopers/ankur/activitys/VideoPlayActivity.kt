package com.bldevelopers.ankur.activitys

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bldevelopers.ankur.databinding.ActivityVideoPlayBinding
import com.bldevelopers.ankur.models.Cat_Detail_Model
import com.bldevelopers.ankur.room.VisitedDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Boolean


class VideoPlayActivity : AppCompatActivity() {
    private lateinit var database: VisitedDatabase
    private lateinit var binding: ActivityVideoPlayBinding
    private lateinit var videoViewModel: VideoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()

        val catVideoNo = intent.getStringExtra("cat_video_id")
        val catVideoUrl = intent.getStringExtra("cat_video_url")
        val cat_title = intent.getStringExtra("cat_video_title")
        val cat_language = intent.getStringExtra("cat_video_lan")
        val videoId = catVideoUrl?.replace("https://youtu.be/", "")


        videoViewModel = ViewModelProvider(this).get(VideoViewModel::class.java)

        videoViewModel.videoPlayer(videoId, binding.youtubePlayerView)

        database =
            Room.databaseBuilder(applicationContext, VisitedDatabase::class.java, "visited").build()

        GlobalScope.launch {
            database.visitedDao().insertVisitedVideo(
                Cat_Detail_Model(
                    id = 0,
                    Integer.parseInt(catVideoNo!!),
                    cat_title!!,
                    catVideoUrl!!,
                    cat_language!!
                )
            )
        }

    }
}
