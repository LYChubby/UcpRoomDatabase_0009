package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.repository.RepositoryJadwal
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

data class JadwalUiState(
    val listJadwal: List<Jadwal> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
)

data class JadwalAppViewModel(
    private val jadwalRepository: RepositoryJadwal
): ViewModel() {

    val jadwalUiState: StateFlow<JadwalUiState> = jadwalRepository.getAllJadwal()
        .filterNotNull()
        .map {
            JadwalUiState(
                listJadwal = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(JadwalUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                JadwalUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = JadwalUiState(
                isLoading = true
            )
        )
}