package com.example.paybackandroidchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.entity.ImagesPixabayList
import com.example.domain.usecase.GetImagesFromLocalStorageUseCase
import com.example.domain.usecase.GetImagesFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 12:07 AM
 */


@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val getImagesFromRemoteUseCase: GetImagesFromRemoteUseCase,
    private val getImagesFromLocalStorageUseCase: GetImagesFromLocalStorageUseCase
) : ViewModel() {

    private val _state =
        MutableStateFlow<ImagesViewStatus>(ImagesViewStatus.Init)
    val state: StateFlow<ImagesViewStatus> get() = _state

    private fun setLoading(isLoading: Boolean) {
        _state.value = ImagesViewStatus.IsLoading(isLoading)
    }
}

sealed class ImagesViewStatus {
    object Init : ImagesViewStatus()
    data class IsLoading(val isLoading: Boolean) : ImagesViewStatus()
    data class SuccessAcceptOffer(val response: ImagesPixabayList) : ImagesViewStatus()
    data class ErrorAcceptOffer(val error: ImagesPixabayList) : ImagesViewStatus()
}