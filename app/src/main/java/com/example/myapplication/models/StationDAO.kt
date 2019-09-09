package com.example.myapplication.models

import androidx.room.*

@Dao
interface StationDAO {
    @Query("SELECT * FROM station")
    fun getAll(): List<Station>

    @Query("SELECT * FROM station WHERE stationId = :stationID")
    fun loadAllByIds(stationID: Int): List<Station>

    @Insert
    fun insertAll(vararg stations: Station)

    @Update
    fun update(vararg stations: Station)

    @Delete
    fun delete(station: Station)
}