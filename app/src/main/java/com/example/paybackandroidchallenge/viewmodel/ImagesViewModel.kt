package com.example.paybackandroidchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.BaseResult
import com.example.common.WrappedErrorResponse
import com.example.domain.entity.ImagesPixabayList
import com.example.domain.usecase.GetImagesFromLocalStorageUseCase
import com.example.domain.usecase.GetImagesFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
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

    fun getImages(search : String) {
        viewModelScope.launch {
            getImagesFromRemoteUseCase.invoke(search)
                .onStart {
                    setLoading(true)
                }
                .catch { exception ->
                    _state.value = ImagesViewStatus.ShowToast(exception.message.toString())
                    setLoading(false)
                }
                .collect { result ->
                    setLoading(false)
                    when (result) {
                        is BaseResult.Success -> _state.value = ImagesViewStatus.SuccessGetImages(result.data)
                        is BaseResult.Error -> _state.value = ImagesViewStatus.ErrorGetImages(result.rawResponse)
                    }
                }
        }
    }
}

sealed class ImagesViewStatus {
    object Init : ImagesViewStatus()
    data class IsLoading(val isLoading: Boolean) : ImagesViewStatus()
    data class ShowToast(val message: String) : ImagesViewStatus()
    data class SuccessGetImages(val response: ImagesPixabayList) : ImagesViewStatus()
    data class ErrorGetImages(val error: WrappedErrorResponse<ImagesPixabayList>) : ImagesViewStatus()
}