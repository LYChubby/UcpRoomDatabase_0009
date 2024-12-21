package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.navigation.DestinasiEditJadwal
import com.example.ucp2.repository.RepositoryJadwal
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateJadwalViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryJadwal: RepositoryJadwal
): ViewModel() {

    var updateJadwalUiState by mutableStateOf(JadwalUIState())
        private set

    private val _id: Int = checkNotNull(savedStateHandle[DestinasiEditJadwal.ID])

    init {
        viewModelScope.launch {
            updateJadwalUiState = repositoryJadwal.getJadwal(_id)
                .filterNotNull()
                .first()
                .toUIStateJadwal()
        }
    }

    fun updateJadwal(jadwalEvent: JadwalEvent) {
        updateJadwalUiState = updateJadwalUiState.copy(
            jadwalEvent = jadwalEvent
        )
    }

    fun validateInput(): Boolean {
        val event = updateJadwalUiState.jadwalEvent
        val errorState = FormJadwalErrorState(
            namaPasien = if (event.namaPasien.isNotEmpty()) null else "Nama pasien tidak boleh kosong",
            namaDokter = if (event.namaDokter.isNotEmpty()) null else "Nama dokter tidak boleh kosong",
            telepon = if (event.telepon.isNotEmpty()) null else "Nomor telepon tidak boleh kosong",
            tanggal = if (event.tanggal.isNotEmpty()) null else "Tanggal tidak boleh kosong",
            status = if (event.status.isNotEmpty()) null else "Status tidak boleh kosong"
        )
        updateJadwalUiState = updateJadwalUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateJadwal() {
        val currentJadwal = updateJadwalUiState.jadwalEvent

        if (validateInput()) {
            viewModelScope.launch {
                try {
                    repositoryJadwal.updateJadwal(currentJadwal.toJadwalEntity())
                    updateJadwalUiState = updateJadwalUiState.copy(
                        snackbarMessage = "Jadwal Berhasil Di Update",
                        jadwalEvent = JadwalEvent(),
                        isEntryValid = FormJadwalErrorState()
                    )
                    println("snackBarMessage Diatur: ${updateJadwalUiState.snackbarMessage}")
                } catch (e: Exception) {
                    updateJadwalUiState = updateJadwalUiState.copy(
                        snackbarMessage = "Jadwal Gagal Diupdate"
                    )
                }
            }
        } else {
            updateJadwalUiState = updateJadwalUiState.copy(
                snackbarMessage = "Jadwal Gagal Diupdate"
            )
        }
    }

    fun resetSnackbarMessage() {
        updateJadwalUiState = updateJadwalUiState.copy(
            snackbarMessage = null
        )
    }
}

fun Jadwal.toUIStateJadwal(): JadwalUIState = JadwalUIState(
    jadwalEvent = this.toDetailJadwalEvent()
)