package com.example.ucp2.ui.view.dokter

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
import com.example.ucp2.data.Spesialis
import com.example.ucp2.navigation.AlamatNavigasi
import com.example.ucp2.ui.customwidget.DynamicSelectedField
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.DokterEvent
import com.example.ucp2.ui.viewmodel.DokterUIState
import com.example.ucp2.ui.viewmodel.DokterViewModel
import com.example.ucp2.ui.viewmodel.FormErrorState
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun FormDokter(
    dokterEvent: DokterEvent = DokterEvent(),
    onValueChange: (DokterEvent) -> Unit = { },
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){

    var choosenDropdown by remember { mutableStateOf("") }
    var listSpesialis = mutableListOf(choosenDropdown)
    Column (
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = dokterEvent.nama,
            onValueChange = {
                onValueChange(dokterEvent.copy(nama = it))
            },
            label = { Text(text = "Nama Dokter") },
            isError = errorState.nama != null,
            placeholder = { Text(text = "Masukkan Nama Dokter")}
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        DynamicSelectedField(
            selectedValue = dokterEvent.spesialis,
            options = Spesialis.option,
            label = "Spesialis",
            onValueChangedEvent = {
                choosenDropdown = it
            }
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = dokterEvent.klinik,
            onValueChange = {
                onValueChange(dokterEvent.copy(klinik = it))
            },
            label = { Text(text = "Nama Klinik") },
            isError = errorState.klinik != null,
            placeholder = { Text(text = "Masukkan Nama Klinik")}
        )
        Text(
            text = errorState.klinik ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = dokterEvent.telepon,
            onValueChange = {
                onValueChange(dokterEvent.copy(telepon = it))
            },
            label = { Text(text = "No Hpr") },
            isError = errorState.telepon != null,
            placeholder = { Text(text = "Masukkan No Hp")}
        )
        Text(
            text = errorState.telepon ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = dokterEvent.jamKerja,
            onValueChange = {
                onValueChange(dokterEvent.copy(jamKerja = it))
            },
            label = { Text(text = "Jam Kerja") },
            isError = errorState.nama != null,
            placeholder = { Text(text = "Masukkan Jam Kerja")}
        )
        Text(
            text = errorState.jamKerja ?: "",
            color = Color.Red
        )
    }
}

@Composable
fun InsertBodyDokter(
    modifier: Modifier = Modifier,
    onValueChange: (DokterEvent) -> Unit,
    uiState: DokterUIState,
    onSaveClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormDokter(
            dokterEvent = uiState.dokterEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

object DestinasiInsertDokter : AlamatNavigasi {
    override val route = "insert_dokter"
}

@Composable
fun InsertDokterView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect (uiState.snackbarMessage){
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackbarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ){
        padding ->
        Column (
            modifier = modifier.fillMaxWidth()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dokter"
            )
            InsertBodyDokter(
                uiState = uiState,
                onValueChange = {updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.saveDokter()
                    }
                    onNavigate()
                }
            )
        }
    }
}