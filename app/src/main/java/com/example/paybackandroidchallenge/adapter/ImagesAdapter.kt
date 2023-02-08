package com.example.paybackandroidchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Hit
import com.example.paybackandroidchallenge.databinding.ImageListItemBinding

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 12:43 AM
 */


internal class ImagesAdapter(var items: List<Hit?>) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ImageListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position]!!)

    inner class ViewHolder(private val binding: ImageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hit) {
            with(binding) {
                binding.tvImageUserName.text = item.user
                Glide.with(binding.root.context).load(item.previewURL).into(binding.ivImage)

                val result: List<String>? = item.tags?.let { tags -> tags.split(",").map { it.trim() } }
                var imageTagsAdapter : ImageTagsAdapter
                result?.let {
                    imageTagsAdapter = ImageTagsAdapter(it)
                    binding.rvImagesTags.apply {
                        layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL,false)
                        isNestedScrollingEnabled = false
                        adapter = imageTagsAdapter

                        imageTagsAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}
