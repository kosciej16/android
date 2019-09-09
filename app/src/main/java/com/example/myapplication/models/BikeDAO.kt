package com.example.myapplication.models

import androidx.room.*


@Dao
interface BikeDAO {
    @Query("SELECT * FROM bike")
    fun getAll(): List<Bike>

    @Insert
    fun insertAll(vararg bikes: Bike)

    @Update
    fun updateUsers(vararg bikes: Bike)

    @Query("SELECT * FROM bike WHERE stationId=:stationId")
    fun findBikesForStation(stationId: Int) : List<Bike>

    @Delete
    fun delete(bike: Bike)
}
