package com.bldevelopers.ankur.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bldevelopers.ankur.R
import com.bldevelopers.ankur.activitys.VideoPlayActivity
import com.bldevelopers.ankur.databinding.ItemCategoryDetailsLayoutBinding
import com.bldevelopers.ankur.models.Cat_Detail_Model
import com.bldevelopers.ankur.room.VisitedDatabase
import com.bumptech.glide.Glide

class Cat_Detail_Adapter : RecyclerView.Adapter<Cat_Detail_Adapter.CatDetailsViewHolder>() {

    inner class CatDetailsViewHolder(val binding: ItemCategoryDetailsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<Cat_Detail_Model>() {
        override fun areItemsTheSame(
            oldItem: Cat_Detail_Model,
            newItem: Cat_Detail_Model
        ): Boolean {
            return oldItem.No == newItem.No
        }

        override fun areContentsTheSame(
            oldItem: Cat_Detail_Model,
            newItem: Cat_Detail_Model
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var cat_detail_model: List<Cat_Detail_Model>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = cat_detail_model.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatDetailsViewHolder {
        return CatDetailsViewHolder(
            ItemCategoryDetailsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatDetailsViewHolder, position: Int) {
        holder.binding.apply {
            val catDetailModels = cat_detail_model[position]

            catDetailId.text = catDetailModels.No.toString()
            categVideoNameTxt.text = catDetailModels.ACTIVITY
            val videoId = catDetailModels.Link?.replace("https://youtu.be/", "")

            val thumbnail: String = "https://img.youtube.com/vi/" + videoId + "/0.jpg"
            Log.d("sfsdfsf", "$thumbnail")

            Glide.with(holder.itemView.context).load(thumbnail).placeholder(R.drawable.logoplace)
                .into(catThumbView)

            holder.itemView.setOnClickListener {
                val intent: Intent =
                    Intent(holder.itemView.context, VideoPlayActivity::class.java)
                intent.putExtra("cat_video_url", catDetailModels.Link)
                intent.putExtra("cat_video_title", catDetailModels.ACTIVITY)
                intent.putExtra("cat_video_id", catDetailModels.No.toString())
                intent.putExtra("cat_video_lan", catDetailModels.Language)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

}