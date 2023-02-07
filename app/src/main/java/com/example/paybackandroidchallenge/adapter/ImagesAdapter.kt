package com.example.paybackandroidchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

    inner class ViewHolder(val binding: ImageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hit) {
            with(binding) {

            }
        }
    }
}
