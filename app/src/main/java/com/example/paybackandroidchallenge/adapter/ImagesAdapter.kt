package com.example.paybackandroidchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.RecyclerItemListener
import com.example.domain.entity.Hit
import com.example.paybackandroidchallenge.databinding.ImageListItemBinding
import com.example.paybackandroidchallenge.viewmodel.ImagesViewModel

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 12:43 AM
 */


internal class ImagesAdapter(var items: List<Hit?>, private var viewModel: ImagesViewModel) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private val onItemSelectedListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(item: Any) {
            viewModel.openImageDetails(item as Hit)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ImageListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding, onItemSelectedListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position]!!)

    inner class ViewHolder(private val binding: ImageListItemBinding, private val onItemSelectedListener: RecyclerItemListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hit) {
            with(binding) {
                binding.tvImageUserName.text = item.user
                Glide.with(binding.root.context).load(item.previewURL).into(binding.ivImage)

                val result: List<String>? = item.tags?.let { tags -> tags.split(",").map { it.trim() } }
                val imageTagsAdapter : ImageTagsAdapter
                result?.let {
                    imageTagsAdapter = ImageTagsAdapter(it)
                    binding.rvImagesTags.apply {
                        layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL,false)
                        isNestedScrollingEnabled = false
                        adapter = imageTagsAdapter

                        imageTagsAdapter.notifyDataSetChanged()
                    }
                }

                root.setOnClickListener {
                    onItemSelectedListener.onItemSelected(item)
                }
            }
        }
    }
}
