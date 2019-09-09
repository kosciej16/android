package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.models.Bike
import com.example.myapplication.models.BikeDAO
import com.example.myapplication.models.Station
import com.example.myapplication.models.StationDAO

@Database(entities = arrayOf(Station::class, Bike::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationDAO(): StationDAO
    abstract fun BikeDAO(): BikeDAO
}