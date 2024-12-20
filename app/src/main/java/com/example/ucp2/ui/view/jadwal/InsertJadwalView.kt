package com.example.ucp2.ui.view.jadwal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.navigation.AlamatNavigasi
import com.example.ucp2.ui.customwidget.DynamicSelectedField
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.DokterEvent
import com.example.ucp2.ui.viewmodel.FormJadwalErrorState
import com.example.ucp2.ui.viewmodel.JadwalEvent
import com.example.ucp2.ui.viewmodel.JadwalUIState
import com.example.ucp2.ui.viewmodel.JadwalViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun FormJadwal(
    jadwalEvent: JadwalEvent = JadwalEvent(),
    dokterEvent: DokterEvent = DokterEvent(),
    onValueChange: (JadwalEvent) -> Unit = { },
    errorState: FormJadwalErrorState = FormJadwalErrorState(),
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = jadwalEvent.namaPasien,
            onValueChange = {
                onValueChange(jadwalEvent.copy(namaPasien = it))
            },
            label = { Text(text = "Nama Pasien") },
            isError = errorState.namaPasien != null,
            placeholder = { Text(text = "Masukkan Nama Pasien") }
        )
        Text(
            text = errorState.namaPasien ?: "",
            color = Color.Red
        )

        DynamicSelectedField(
            selectedValue = jadwalEvent.namaDokter,
            options = dokterEvent.nama,
            label = "Spesialis",
            onValueChangedEvent = {
                onValueChange(jadwalEvent.copy(namaDokter = it))
            }
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = jadwalEvent.telepon,
            onValueChange = {
                onValueChange(jadwalEvent.copy(telepon = it))
            },
            label = { Text(text = "No Hp") },
            isError = errorState.telepon != null,
            placeholder = { Text(text = "Masukkan No Hp") }
        )
        Text(
            text = errorState.telepon ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = jadwalEvent.tanggal,
            onValueChange = {
                onValueChange(jadwalEvent.copy(tanggal = it))
            },
            label = { Text(text = "Tanggal") },
            isError = errorState.tanggal != null,
            placeholder = { Text(text = "Masukkan Tanggal") }
        )
        Text(
            text = errorState.tanggal ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = jadwalEvent.status,
            onValueChange = {
                onValueChange(jadwalEvent.copy(status = it))
            },
            label = { Text(text = "Status") },
            isError = errorState.status != null,
            placeholder = { Text(text = "Masukkan Status") }
        )
        Text(
            text = errorState.status ?: "",
            color = Color.Red
        )
    }
}

@Composable
fun InsertBodyJadwal(
    modifier: Modifier = Modifier,
    onValueChange: (JadwalEvent) -> Unit,
    uiState: JadwalUIState,
    onSaveClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormJadwal(
            jadwalEvent = uiState.jadwalEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Simpan")
        }
    }
}

object DestinasiInsertJadwal : AlamatNavigasi {
    override val route = "insert_jadwal"
}

@Composable
fun InsertJadwalView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: JadwalViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackbarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        padding ->
        Column(
            modifier = modifier.fillMaxWidth()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Jadwal"
            )

            InsertBodyJadwal(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.saveJadwal()
                    }
                    onNavigate()
                }
            )
        }
    }
}