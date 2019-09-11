package com.example.myapplication.models

import androidx.room.*


@Dao
interface BikeDAO {
    @Query("SELECT * FROM bike")
    fun getAll(): List<Bike>

    @Query("SELECT * FROM bike where bikeId = :bikeId")
    fun getById(bikeId: Int): Bike

    @Insert
    fun insertAll(vararg bikes: Bike)

    @Query("UPDATE bike SET ring= :ring WHERE bikeId = :bikeId")
    fun updateBikeRing(bikeId: Int, ring: Int)

    @Query("SELECT * FROM bike WHERE stationId=:stationId")
    fun findBikesForStation(stationId: Int) : List<Bike>

    @Delete
    fun delete(bike: Bike)
}
