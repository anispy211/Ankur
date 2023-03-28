package com.bldevelopers.ankur.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bldevelopers.ankur.R
import com.bldevelopers.ankur.adapters.CategoriesAdapter
import com.bldevelopers.ankur.databinding.ActivityMainBinding
import com.bldevelopers.ankur.network.RetrofitInstance
import okio.IOException
import retrofit2.HttpException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible  = true
            val response = try {
                RetrofitInstance.api.getCategories()
            }catch (e:IOException){
                Log.e(TAG, "You might not have internet connection" )
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG, "HttpException, unexpected response" )
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                categoriesAdapter.catList = response.body()!!
            }else{
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }

    }

    private fun setupRecyclerView() = binding.rvCat.apply {
        categoriesAdapter = CategoriesAdapter()
        adapter = categoriesAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_about -> startActivity(Intent(this,AboutActivity::class.java))
            R.id.tutorial -> Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}