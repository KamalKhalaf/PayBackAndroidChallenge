package com.example.paybackandroidchallenge.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cache.room.ImageModel
import com.example.cache.room.ImagesDao
import com.example.common.BaseResult
import com.example.common.SingleEvent
import com.example.common.WrappedErrorResponse
import com.example.domain.entity.Hit
import com.example.domain.entity.ImagesPixabayList
import com.example.domain.usecase.GetImagesFromLocalStorageUseCase
import com.example.domain.usecase.GetImagesFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

/**
 * @Created by: Kamal.Farghali
 * @Date: 08/02/2023 : 12:07 AM
 */


@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val getImagesFromRemoteUseCase: GetImagesFromRemoteUseCase,
    private val getImagesFromLocalStorageUseCase: GetImagesFromLocalStorageUseCase,
    private val imagesDao: ImagesDao
) : ViewModel() {

    private val _state =
        MutableStateFlow<ImagesViewStatus>(ImagesViewStatus.Init)
    val state: StateFlow<ImagesViewStatus> get() = _state

    private val keywordFlow = MutableSharedFlow<String>()
    private var searchJob: Job? = null

    private fun setLoading(isLoading: Boolean) {
        _state.value = ImagesViewStatus.IsLoading(isLoading)
    }

    private val _openImageDetails = MutableLiveData<SingleEvent<Hit>>()
    val openImageDetails: LiveData<SingleEvent<Hit>> get() = _openImageDetails

    fun openImageDetails(imageDetails: Hit) {
        _openImageDetails.value = SingleEvent(imageDetails)
    }

    fun searchImages(keyword: String) {
        viewModelScope.launch {
            keywordFlow.emit(keyword)
        }
    }

    init {
        viewModelScope.launch {
            keywordFlow.collectLatest {
                searchJob?.cancel()
                getImagesFromRemoteUseCase.invoke(it)
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
                            is BaseResult.Success -> {
                                _state.value = ImagesViewStatus.SuccessGetImages(result.data)
                            }
                            is BaseResult.Error -> _state.value = ImagesViewStatus.ErrorGetImages(result.rawResponse)
                        }
                    }
            }
        }
    }

    fun resetSearch() {
        searchJob?.cancel()
    }
}

sealed class ImagesViewStatus {
    object Init : ImagesViewStatus()
    data class IsLoading(val isLoading: Boolean) : ImagesViewStatus()
    data class ShowToast(val message: String) : ImagesViewStatus()
    data class SuccessGetImages(val response: ImagesPixabayList) : ImagesViewStatus()
    data class ErrorGetImages(val error: WrappedErrorResponse<ImagesPixabayList>) : ImagesViewStatus()
}