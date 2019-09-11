package com.example.myapplication.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.AppDatabase
import com.example.myapplication.R
import com.example.myapplication.models.Bike

class AddBikeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_ring)
    }

    fun addRingNumber(view : View) {
        val bike = findViewById<EditText>(R.id.bikeNumber).editableText.toString()
        val ring = findViewById<EditText>(R.id.ringNumber).editableText.toString()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        db.BikeDAO().updateBikeRing(bike.toInt(), ring.toInt())
    }
}
