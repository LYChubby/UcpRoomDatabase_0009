package com.example.ucp2.ui.view.dokter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.data.entity.Dokter

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
        Column (
            modifier = Modifier.padding(8.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dokter.nama,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dokter.spesialis,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dokter.jamKerja,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}