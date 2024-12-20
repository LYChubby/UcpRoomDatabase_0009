package com.example.ucp2.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiJadwal : AlamatNavigasi {
    override val route = "jadwal"
}