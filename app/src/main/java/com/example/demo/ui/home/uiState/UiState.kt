package com.example.demo.ui.home.uiState

sealed class UiState {
    data object StoreData : UiState()
    data object InProgress : UiState()
}