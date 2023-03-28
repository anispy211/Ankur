package com.bldevelopers.ankur.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bldevelopers.ankur.R
import com.bldevelopers.ankur.activitys.CategoriesDetailsActivity
import com.bldevelopers.ankur.databinding.ItemCategoryLayoutBinding
import com.bldevelopers.ankur.models.categories
import com.bumptech.glide.Glide

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: ItemCategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<categories>() {
        override fun areItemsTheSame(oldItem: categories, newItem: categories): Boolean {
            return oldItem.catgory_id == newItem.catgory_id
        }

        override fun areContentsTheSame(oldItem: categories, newItem: categories): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var catList: List<categories>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = catList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            val todo = catList[position]
            catId.text = todo.catgory_id
            categNameTxt.text = todo.catgory_name
            Glide.with(holder.itemView.context).load(todo.catgory_icon)
                .placeholder(R.drawable.logoplace).into(catImageView)

            holder.itemView.setOnClickListener {

                val intent: Intent =
                    Intent(holder.itemView.context, CategoriesDetailsActivity::class.java)
                intent.putExtra("cat_detail", todo.catgory_name)
                intent.putExtra("category_detail_url", todo.category_detail_url)
                holder.itemView.context.startActivity(intent)
            }

        }

    }

}