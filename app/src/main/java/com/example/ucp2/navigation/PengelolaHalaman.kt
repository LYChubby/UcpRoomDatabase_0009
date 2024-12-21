package com.example.ucp2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.dokter.DestinasiInsertDokter
import com.example.ucp2.ui.view.dokter.HomeDokterView
import com.example.ucp2.ui.view.dokter.InsertDokterView
import com.example.ucp2.ui.view.jadwal.DestinasiInsertJadwal
import com.example.ucp2.ui.view.jadwal.DetailJadwalView
import com.example.ucp2.ui.view.jadwal.HomeJadwalView
import com.example.ucp2.ui.view.jadwal.InsertJadwalView
import com.example.ucp2.ui.view.jadwal.UpdateJadwalView

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
                onJadwal = { navController.navigate(DestinasiJadwal.route)
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
            HomeJadwalView(
                onDetailClick = {
//                    navController.navigate(DestinasiDetail.route)
                },
                onAddJadwal = { navController.navigate(DestinasiInsertJadwal.route)
                },
                onDokter = { navController.navigate(DestinasiHome.route)
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

        composable(
            DestinasiDetailJadwal.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailJadwal.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiDetailJadwal.ID)
            id?.let { id ->
                DetailJadwalView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiEditJadwal.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiEditJadwal.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiEditJadwal.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}