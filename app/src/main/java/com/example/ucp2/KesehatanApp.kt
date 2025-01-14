package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependeciesinjection.DokterApp
import com.example.ucp2.dependeciesinjection.JadwalApp

class KesehatanApp: Application()  {
    lateinit var dokterApp: DokterApp
    lateinit var jadwalApp: JadwalApp

    override fun onCreate() {
        super.onCreate()
        dokterApp = DokterApp(this)
        jadwalApp = JadwalApp(this)
    }
}