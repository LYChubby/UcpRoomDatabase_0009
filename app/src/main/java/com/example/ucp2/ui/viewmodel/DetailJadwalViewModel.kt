package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Jadwal

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

fun Jadwal.toDetailJadwalEvent(): JadwalEvent {
    return JadwalEvent(
        id = id,
        namaPasien = namaPasien,
        namaDokter = namaDokter,
        telepon = telepon,
        tanggal = tanggal,
        status = status
    )
}