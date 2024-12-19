package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.JadwalDao
import com.example.ucp2.data.entity.Jadwal

@Database(entities = [Jadwal::class], version = 1, exportSchema = false)
abstract class JadwalDatabase: RoomDatabase() {

    abstract fun jadwalDao(): JadwalDao

    companion object {
        @Volatile
        private var Instance: JadwalDatabase? = null

        fun getDatabase(context: Context): JadwalDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    JadwalDatabase::class.java,
                    "JadwalDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}