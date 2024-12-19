package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Dokter

data class DokterEvent(
    val id: Int,
    val nama: String,
    val spesialis: String,
    val klinik: String,
    val telepon: String,
    val jamKerja: String
)

fun DokterEvent.toDokterEntity(): Dokter = Dokter (
    id = id,
    nama = nama,
    spesialis = spesialis,
    klinik = klinik,
    telepon = telepon,
    jamKerja = jamKerja
)

data class FormErrorState(
    val id: Int? = null,
    val nama: String? = null,
    val spesialis: String? = null,
    val klinik: String? = null,
    val telepon: String? = null,
    val jamKerja: String? = null
) {
    fun isValid(): Boolean {
        return id == null && nama == null && spesialis == null &&
                klinik == null && telepon == null && jamKerja == null
    }
}