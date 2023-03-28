package com.bldevelopers.ankur.activitys

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bldevelopers.ankur.adapters.Cat_Detail_Adapter
import com.bldevelopers.ankur.databinding.ActivityCategoriesDetailsBinding
import com.bldevelopers.ankur.network.RetrofitInstance
import okio.IOException
import retrofit2.HttpException


class CategoriesDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesDetailsBinding
    private lateinit var catDetailAdapter: Cat_Detail_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cat_details_url = intent.getStringExtra("category_detail_url")
        val intent = intent.getStringExtra("cat_detail")
        setupRecyclerView()

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "$intent"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        lifecycleScope.launchWhenCreated {
            binding.progressBarDetails.isVisible = true
            val response = try {
                RetrofitInstance.api.getCatDetails(cat_details_url!!)
            } catch (e: IOException) {
                Log.e(TAG, "You might not have internet connection")
                binding.progressBarDetails.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBarDetails.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                catDetailAdapter.cat_detail_model = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBarDetails.isVisible = false
        }


    }

    private fun setupRecyclerView() = binding.rvCatDetails.apply {
        catDetailAdapter = Cat_Detail_Adapter()
        adapter = catDetailAdapter
        layoutManager = LinearLayoutManager(this@CategoriesDetailsActivity)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}