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

