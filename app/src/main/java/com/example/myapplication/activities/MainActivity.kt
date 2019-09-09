package com.example.myapplication.activities

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import android.widget.SearchView
import androidx.room.Room
import com.example.myapplication.*
import com.example.myapplication.models.Station

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var adapter: StationAdapter

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.station_list)

        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }



        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        val col = db.stationDAO().getAll()
        val list = findViewById<ListView>(R.id.station_list)
        adapter = StationAdapter(this, col as ArrayList<Station>)
        list.adapter = adapter
        val editsearch = findViewById<SearchView>(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }

    fun doMySearch(query: String) {
        Log.e("cos", query)
    }

    fun addRingNumber(view : View) {
        val intent = Intent(this, AddBikeActivity::class.java)
        this.startActivity(intent)
    }

    fun sync(view : View) {
        SyncBikesTask().execute(applicationContext)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean = false

    override fun onQueryTextChange(text: String): Boolean {
        adapter.filter(text)
        return false
    }
}
