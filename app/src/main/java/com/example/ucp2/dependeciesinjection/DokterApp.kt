package com.example.ucp2.dependeciesinjection

import android.content.Context
import com.example.ucp2.data.database.KesehatanDatabase
import com.example.ucp2.repository.LocalRepositoryDokter
import com.example.ucp2.repository.RepositoryDokter

interface InterfaceDokterApp {
    val repositoryDokter: RepositoryDokter
}

class DokterApp(private val context: Context) : InterfaceDokterApp {
    override val repositoryDokter: RepositoryDokter by lazy {
        LocalRepositoryDokter(KesehatanDatabase.getDatabase(context).dokterDao())
    }
}