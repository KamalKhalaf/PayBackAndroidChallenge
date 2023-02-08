package com.example.paybackandroidchallenge

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.*
import com.example.domain.entity.Hit
import com.example.paybackandroidchallenge.adapter.ImagesAdapter
import com.example.paybackandroidchallenge.base.BaseFragment
import com.example.paybackandroidchallenge.databinding.FragmentImagesListBinding
import com.example.paybackandroidchallenge.viewmodel.ImagesViewModel
import com.example.paybackandroidchallenge.viewmodel.ImagesViewStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ImagesListFragment :
    BaseFragment<FragmentImagesListBinding>(FragmentImagesListBinding::inflate) {

    private val viewmodel: ImagesViewModel by viewModels()
    private lateinit var imagesAdapter: ImagesAdapter

    override fun initViews() {

        viewmodel.getImages()
        viewmodel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach { status -> handleResponse(status) }
            .launchIn(lifecycleScope)

        observeEvent(viewmodel.openImageDetails, ::navigateToDetailsScreen)

        binding.etSearch.doAfterTextChanged { text ->
            if (text != null) {
                if (text.isEmpty()) {
                    viewmodel.resetSearch()
                } else {
                    viewmodel.searchImages(text.toString().replace(" ", "+"))
                }
            }
        }
    }

    private fun navigateToDetailsScreen(card: SingleEvent<Hit>) {
        card.getContentIfNotHandled()?.let {
            navigate(ImagesListFragmentDirections.actionImagesListFragmentToImageDetailsFragment(it))
        }
    }

    private fun handleResponse(status: ImagesViewStatus) {
        when (status) {
            is ImagesViewStatus.IsLoading -> if (isAdded && status.isLoading) {
                binding.pbLoading.root.visible()
                binding.rvImages.gone()
            } else {
                binding.pbLoading.root.gone()
                binding.rvImages.visible()
            }
            is ImagesViewStatus.ShowToast -> showShortToast(status.message)
            is ImagesViewStatus.SuccessGetImages -> {
                val response = status.response
                response.hits?.let {
                    imagesAdapter = ImagesAdapter(it, viewmodel)
                    binding.rvImages.apply {
                        layoutManager = LinearLayoutManager(requireActivity())
                        isNestedScrollingEnabled = false
                        adapter = imagesAdapter
                        imagesAdapter.notifyDataSetChanged()
                    }
                }
            }
            is ImagesViewStatus.ErrorGetImages -> {
                val response = status.error

            }
            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}