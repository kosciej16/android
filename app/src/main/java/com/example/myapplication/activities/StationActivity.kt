package com.example.myapplication.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import android.widget.SearchView
import androidx.room.Room
import com.example.myapplication.AppDatabase
import com.example.myapplication.BikeAdapter
import com.example.myapplication.R
import com.example.myapplication.models.Bike

class StationActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var adapter: BikeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bike_list)

        // Verify the action and get the query

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        val stationID = intent.getIntExtra("your_extra", 0)
        Log.e("STACJA", stationID.toString())
        val col = db.BikeDAO().findBikesForStation(stationID)
        val list = findViewById<ListView>(R.id.bike_list)
        Log.e("STACJA", col.toString())
        adapter = BikeAdapter(this, col as ArrayList<Bike>)
        list.adapter = adapter
//        val editsearch = findViewById<SearchView>(R.id.search);
//        editsearch.setOnQueryTextListener(this);
    }

    override fun onQueryTextSubmit(p0: String?): Boolean = false

    override fun onQueryTextChange(text: String): Boolean {
        adapter.filter(text)
        return false
    }

}
