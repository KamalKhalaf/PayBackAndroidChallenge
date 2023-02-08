package com.example.paybackandroidchallenge

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.gone
import com.example.common.visible
import com.example.paybackandroidchallenge.adapter.ImagesAdapter
import com.example.paybackandroidchallenge.base.BaseFragment
import com.example.paybackandroidchallenge.databinding.FragmentImagesListBinding
import com.example.paybackandroidchallenge.viewmodel.ImagesViewModel
import com.example.paybackandroidchallenge.viewmodel.ImagesViewStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ImagesListFragment : BaseFragment<FragmentImagesListBinding>(FragmentImagesListBinding::inflate) {

    private val viewmodel: ImagesViewModel by viewModels()
    private lateinit var imagesAdapter : ImagesAdapter

    override fun initViews() {

        viewmodel.getImages("fruits")
        viewmodel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach { status -> handleResponse(status) }
            .launchIn(lifecycleScope)

    }

    private fun handleResponse(status: ImagesViewStatus) {
        when (status) {
            is ImagesViewStatus.IsLoading -> if (isAdded && status.isLoading) binding.pbLoading.root.visible() else binding.pbLoading.root.gone()
            is ImagesViewStatus.ShowToast -> showShortToast(status.message)
            is ImagesViewStatus.SuccessGetImages -> {
                val response = status.response
                response.hits?.let { imagesAdapter = ImagesAdapter(it) }
                binding.rvImages.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    isNestedScrollingEnabled = false
                    adapter = imagesAdapter
                    imagesAdapter.notifyDataSetChanged()
                }
            }
            is ImagesViewStatus.ErrorGetImages -> {
                val response = status.error

            }
            else -> {
            }
        }
    }
}