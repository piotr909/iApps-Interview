package com.piotrapps.iappsinterview.ui.list.recycler

sealed class PhotoUiState {

    object Loading: PhotoUiState()

    data class Success(val photos: List<PhotoItem>) : PhotoUiState()

    object Error: PhotoUiState()
}
