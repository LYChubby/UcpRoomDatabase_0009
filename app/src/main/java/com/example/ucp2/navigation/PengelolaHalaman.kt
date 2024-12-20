package com.example.ucp2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.view.dokter.DestinasiInsertDokter
import com.example.ucp2.ui.view.dokter.HomeDokterView
import com.example.ucp2.ui.view.dokter.InsertDokterView
import com.example.ucp2.ui.view.jadwal.DestinasiInsertJadwal
import com.example.ucp2.ui.view.jadwal.HomeJadwalView
import com.example.ucp2.ui.view.jadwal.InsertJadwalView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = DestinasiHome.route) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeDokterView(
                onCardClick = {
//                    navController.navigate(DestinasiDetail.route)
                },
                onAddDokter = { navController.navigate(DestinasiInsertDokter.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertDokter.route
        ) {
            InsertDokterView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiJadwal.route
        ) {
            HomeDokterView(
                onCardClick = {
//                    navController.navigate(DestinasiDetail.route)
                },
                onJadwal = { navController.navigate(DestinasiJadwal.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiHome.route
        ) {
            HomeJadwalView(
                onDetailClick = {
//                    navController.navigate(DestinasiDetail.route)
                },
                onAddJadwal = { navController.navigate(DestinasiHome.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiJadwal.route
        ) {
            HomeJadwalView(
                onDetailClick = {
//                    navController.navigate(DestinasiDetail.route)
                },
                onAddJadwal = { navController.navigate(DestinasiInsertJadwal.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertJadwal.route
        ) {
            InsertJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}