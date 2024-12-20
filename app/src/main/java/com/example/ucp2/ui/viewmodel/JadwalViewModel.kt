package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Jadwal

object IDJadwalGenerator {
    private var currentId = 0
    fun generateId(): Int {
        currentId += 1
        return currentId
    }
}

data class JadwalEvent(
    val id: Int = IDJadwalGenerator.generateId(),
    val namaDokter: String = "",
    val namaPasien: String= "",
    val telepon: String = "",
    val tanggal: String = "",
    val status: String = "",
)

fun JadwalEvent.toJadwalEntity(): Jadwal = Jadwal(
    id = id,
    namaDokter = namaDokter,
    namaPasien = namaPasien,
    telepon = telepon,
    tanggal = tanggal,
    status = status
)

data class FormJadwalErrorState(
    val namaDokter: String? = null,
    val namaPasien: String? = null,
    val telepon: String? = null,
    val tanggal: String? = null,
    val status: String? = null
)