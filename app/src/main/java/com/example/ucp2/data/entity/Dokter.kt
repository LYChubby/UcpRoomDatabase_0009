package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dokter")
data class Dokter (
    @PrimaryKey
    val id: Int,
    val nama: String,
    val spesialis: String,
    val klinik: String,
    val telepon: String,
    val jamKerja: String
)