package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

@Dao
interface JadwalDao {

    @Insert
    suspend fun insertJadwal(jadwal: Jadwal)

    @Insert
    suspend fun updateJadwal(jadwal: Jadwal)

    @Insert
    suspend fun deleteJadwal(jadwal: Jadwal)

    @Query("SELECT * FROM jadwal ORDER BY tanggal ASC")
    fun getAllJadwal(): Flow<List<Jadwal>>

    @Query("SELECT * FROM jadwal WHERE id = :id")
    fun getJadwalById(id: Int): Flow<Jadwal?>
}