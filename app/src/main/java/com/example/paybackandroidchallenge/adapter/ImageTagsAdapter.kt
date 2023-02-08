package com.example.paybackandroidchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Hit
import com.example.paybackandroidchallenge.databinding.ImageListItemBinding
import com.example.paybackandroidchallenge.databinding.ImageTagListItemBinding

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 12:43 AM
 */


internal class ImageTagsAdapter(var items: List<String>) : RecyclerView.Adapter<ImageTagsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ImageTagListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position]!!)

    inner class ViewHolder(private val binding: ImageTagListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
                binding.tvTagName.text = item
            }
        }
    }
}
