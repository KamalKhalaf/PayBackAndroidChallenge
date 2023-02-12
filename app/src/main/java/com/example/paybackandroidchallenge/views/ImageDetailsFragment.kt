package com.example.paybackandroidchallenge.views

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.paybackandroidchallenge.adapter.ImageTagsAdapter
import com.example.paybackandroidchallenge.base.BaseFragment
import com.example.paybackandroidchallenge.databinding.FragmentImageDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment : BaseFragment<FragmentImageDetailsBinding>(FragmentImageDetailsBinding::inflate) {

    private val args : ImageDetailsFragmentArgs by navArgs()
    override fun initViews() {

        val result: List<String>? = args.imageDetails.tags?.let { tags -> tags.split(",").map { it.trim() } }
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

        binding.apply {
            Glide.with(binding.root.context).load(args.imageDetails.previewURL)
                .into(ivImage)
            tvUserName.text = args.imageDetails.user
            tvNumberComments.text = args.imageDetails.comments.toString()
            tvNumberDownloads.text = args.imageDetails.downloads.toString()
            tvNumberLikes.text = args.imageDetails.likes.toString()
        }
    }
}