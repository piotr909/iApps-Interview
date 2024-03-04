package com.piotrapps.iappsinterview.ui.list.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotrapps.domain.usecase.DownloadDataUseCase
import com.piotrapps.domain.usecase.GetPhotoItemsUseCase
import com.piotrapps.iappsinterview.ui.list.recycler.PhotoItem
import com.piotrapps.iappsinterview.ui.list.recycler.PhotoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotoItemsUseCase: GetPhotoItemsUseCase,
    private val downloadDataUseCase: DownloadDataUseCase
) : ViewModel() {

    private val _photosUiState = MutableStateFlow<PhotoUiState>(PhotoUiState.Loading)
    val photosUiState = _photosUiState.asStateFlow()

    init {
        viewModelScope.launch {
            downloadDataUseCase()
                .collect()
        }

        viewModelScope.launch {
            getPhotoItemsUseCase()
                .catch {
                    _photosUiState.value = PhotoUiState.Error
                }
                .collect {
                    _photosUiState.value = PhotoUiState.Success(it.map {
                        PhotoItem(it.imageUrl, it.description, it.link)
                    })
                }
        }
    }
}