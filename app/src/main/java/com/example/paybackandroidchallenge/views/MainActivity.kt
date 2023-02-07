package com.example.paybackandroidchallenge.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.paybackandroidchallenge.R
import com.example.paybackandroidchallenge.viewmodel.ImagesViewModel
import com.example.paybackandroidchallenge.viewmodel.ImagesViewStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewmodel: ImagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewmodel.getImages("fruits")
        viewmodel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach { status -> handleResponse(status) }
            .launchIn(lifecycleScope)

    }

    private fun handleResponse(status: ImagesViewStatus) {
        when (status) {
            is ImagesViewStatus.IsLoading ->
                if (status.isLoading) {
                    var xx = 4
                }else {
                    var xc = 5
                }

            is ImagesViewStatus.ShowToast -> Toast.makeText(this, status.message ,Toast.LENGTH_LONG).show()
            is ImagesViewStatus.SuccessGetImages -> {
                val response = status.response
                var x = 3
            }
            is ImagesViewStatus.ErrorGetImages -> {
                val response = status.error
                var x = 3
            }
            else -> {
            }
        }
    }
}