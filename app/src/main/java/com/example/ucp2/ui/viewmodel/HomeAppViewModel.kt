package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Dokter

data class HomeUiState(
    val dokterList: List<Dokter> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
)