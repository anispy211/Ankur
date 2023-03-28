package com.bldevelopers.ankur.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bldevelopers.ankur.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "About"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}