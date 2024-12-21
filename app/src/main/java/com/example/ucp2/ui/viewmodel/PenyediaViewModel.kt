package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KesehatanApp

object PenyediaViewModel  {

    val Factory = viewModelFactory {
        initializer {
            DokterViewModel(
                kesehatanApp().dokterApp.repositoryDokter
            )
        }
        initializer {
            HomeAppViewModel(
                kesehatanApp().dokterApp.repositoryDokter
            )
        }
        initializer {
            JadwalViewModel(
                kesehatanApp().jadwalApp.repositoryJadwal
            )
        }
        initializer {
            JadwalAppViewModel(
                kesehatanApp().jadwalApp.repositoryJadwal
            )
        }
    }
}

fun CreationExtras.kesehatanApp(): KesehatanApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KesehatanApp)