package com.example.ucp2.ui.view.dokter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ucp2.data.Spesialis
import com.example.ucp2.navigation.AlamatNavigasi
import com.example.ucp2.ui.customwidget.DynamicSelectedField
import com.example.ucp2.ui.viewmodel.DokterEvent
import com.example.ucp2.ui.viewmodel.DokterUIState
import com.example.ucp2.ui.viewmodel.FormErrorState

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

