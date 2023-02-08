package com.example.paybackandroidchallenge.views

import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.paybackandroidchallenge.base.BaseActivity
import com.example.paybackandroidchallenge.databinding.ActivityMainBinding
import com.example.paybackandroidchallenge.viewmodel.ImagesViewModel
import com.example.paybackandroidchallenge.viewmodel.ImagesViewStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewmodel: ImagesViewModel by viewModels()

    override fun initViews() {
        viewmodel.getImages("fruits")
        viewmodel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach { status -> handleResponse(status) }
            .launchIn(lifecycleScope)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

    override fun initConfigurations() {

    }

    private fun handleResponse(status: ImagesViewStatus) {
        when (status) {
            is ImagesViewStatus.IsLoading ->
                if (status.isLoading) {
                }else {
                }

            is ImagesViewStatus.ShowToast -> Toast.makeText(this, status.message ,Toast.LENGTH_LONG).show()
            is ImagesViewStatus.SuccessGetImages -> {
                val response = status.response
            }
            is ImagesViewStatus.ErrorGetImages -> {
                val response = status.error
            }
            else -> {
            }
        }
    }
}