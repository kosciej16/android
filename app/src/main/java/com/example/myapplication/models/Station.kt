package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Station(
    @PrimaryKey val stationId: Int,
//    @ColumnInfo(name = "lat") val lat: Double = 0.0,
//    @ColumnInfo(name = "lng") val lng: Double = 0.0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "free_racks") val freeRacks: Int = 0
)

