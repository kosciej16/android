package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Station::class,
    parentColumns = arrayOf("stationId"),
    childColumns = arrayOf("stationId"),
    onDelete = ForeignKey.CASCADE)))
class Bike(
    @PrimaryKey val bikeId: Int,
    @ColumnInfo(name = "ring") val ring: Int = 0,
    @ColumnInfo(name = "stationId") val stationId: Int
)
