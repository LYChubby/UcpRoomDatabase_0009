package com.example.ucp2.ui.viewmodel

data class DetailJadwalUiState(
    val detailJadwalUiState: JadwalEvent = JadwalEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
) {
    val isUiJadwalEventEmpty: Boolean
    get() = detailJadwalUiState == JadwalEvent()

    val isUiJadwalEventNotEmpty: Boolean
    get() = detailJadwalUiState != JadwalEvent()
}