package com.example.ucp2.dependeciesinjection

import android.content.Context
import com.example.ucp2.data.database.JadwalDatabase
import com.example.ucp2.repository.LocalRepositoryJadwal
import com.example.ucp2.repository.RepositoryJadwal

interface InterfaceJadwalApp {
    val repositoryJadwal: RepositoryJadwal
}

class JadwalApp(private val context: Context) : InterfaceJadwalApp {
    override val repositoryJadwal: RepositoryJadwal by lazy {
        LocalRepositoryJadwal(JadwalDatabase.getDatabase(context).jadwalDao())
    }
}