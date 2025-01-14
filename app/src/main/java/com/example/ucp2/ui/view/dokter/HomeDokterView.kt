package com.example.ucp2.ui.view.dokter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.HomeAppViewModel
import com.example.ucp2.ui.viewmodel.HomeUiState
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn (ExperimentalMaterial3Api::class)
@Composable
fun CardDokter (
    dokter: Dokter,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit = { }
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onCardClick
    ){
        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Filled.Person,
                modifier = Modifier.size(60.dp),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(10.dp))

            Column (
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = dokter.nama,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Filled.MedicalServices,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = dokter.spesialis,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = when (dokter.spesialis) {
                            "Dokter Gigi" -> Color.Yellow
                            "Dokter Jantung" -> Color.Red
                            "Dokter Mata" -> Color.Green
                            "Dokter Kecantikan" -> Color.Blue
                            else -> Color.Black
                        }
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Filled.AccessTime,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = dokter.jamKerja,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ListDokter(
    dokterList: List<Dokter>,
    modifier: Modifier = Modifier,
    onCardClick: (String) -> Unit = { }
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = dokterList,
            itemContent = { dokter ->
                CardDokter(
                    dokter = dokter,
                    onCardClick = { onCardClick(dokter.id.toString()) }
                )
            }
        )
    }
}

@Composable
fun BodyHomeDokterView(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier,
    onCardClick: (String) -> Unit = { }
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiState.isLoading -> {
            Box (
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            LaunchedEffect(homeUiState.errorMessage)  {
                homeUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeUiState.dokterList.isEmpty() -> {
            Box (
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak Ada Data Dokter",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListDokter(
                dokterList = homeUiState.dokterList,
                onCardClick = {
                    onCardClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeDokterView(
    viewModel: HomeAppViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDokter: () -> Unit = { },
    onJadwal: () -> Unit = { },
    onCardClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .background(color = Color(0xFFFF6F61))
                .fillMaxWidth()
        ) {
            // Header Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.fri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Hi, Fauzi Taufik",
                        color = Color.White
                    )
                    Text(
                        text = "Selamat Datang Di DisneyMedical",
                        color = Color.White
                    )
                }
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification Icon",
                    tint = Color.White
                )
            }

            // Search Box
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(color = Color.White),
                placeholder = { Text(text = "Cari Dokter") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = "Search Icon"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    onClick = { onAddDokter() },
                    modifier = Modifier.weight(0.5f)
                        .padding(end = 5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A3C81))
                ) {
                    Text(text = "Tambah Dokter")
                }
                Button(
                    onClick = { onJadwal() },
                    modifier = Modifier.weight(0.5f)
                        .padding(start = 5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A3C81))
                ) {
                    Text(text = "Lihat Jadwal")
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(color = Color.White)
                    .padding(16.dp)
            ) {

                Text(text = "Pilih Dokter Yang Anda Inginkan", fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
                Text(text = "Dokter Kami Siap Melayani Anda",  fontSize = 13.sp,
                    fontWeight = FontWeight.Thin)

                Spacer(modifier = Modifier.size(10.dp))

                // Body Content
                val homeUiState by viewModel.homeUiState.collectAsState()

                BodyHomeDokterView(
                    homeUiState = homeUiState,
                    onCardClick = { onCardClick(it) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
