package com.example.ucp2.data.entity

@Entity(tableName = "jadwal")

data class Jadwal (
    @PrimaryKey
    val id: Int,
    val namaDokter: String,
    val namaPasien: String,
    val telepon: String,
    val tanggal: String,
    val status: String
)