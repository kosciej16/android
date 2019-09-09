package com.example.myapplication

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Room
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.example.myapplication.utils.toIntOrZero
import com.example.myapplication.utils.toStringOrEmpty
import com.example.myapplication.models.Bike
import com.example.myapplication.models.Station
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.StringReader

class SyncBikesTask : AsyncTask<Context, Void, Unit>() {

    override fun doInBackground(vararg p0: Context) {
        val db = Room.databaseBuilder(
            p0[0],
            AppDatabase::class.java, "database-name"
        ).build()
        val doc : Document = Jsoup.connect("https://www.veturilo.waw.pl/mapa-stacji/").get()
        val nodeData = getNodeWithBikes(doc)
        val jsonString = getJSONPart(nodeData)
        Log.e("COSIK", jsonString)
        val (stations, bikes) = parseJSON(jsonString)
        insert(stations, bikes, p0[0])
        Log.e("AC", db.stationDAO().getAll().size.toString())
    }

    fun getNodeWithBikes(doc: Document) : String {
        val scriptTags : Elements = doc.getElementsByTag("script")
        for (tag in scriptTags) {
            val nodes = tag.dataNodes().filter { node -> node.wholeData.contains("NEXTBIKE_PLACES_DB") }
            if (nodes.isNotEmpty()) {
                return nodes[0].wholeData
            }
        }
        return ""
    }

    fun getJSONPart(str: String) : String {
        val regex = Regex("NEXTBIKE_PLACES_DB = '(.*)'")
        val match = regex.find(str)
        val jsonString = match?.groups?.get(1)?.value
        return jsonString.toStringOrEmpty()
    }

    fun parseJSON(str: String) : Pair<List<Station>, List<Bike>> {
        val parsed = Klaxon().parseJsonArray(StringReader(str))
        val tmp = parsed["places"][0] as JsonArray<JsonObject>
        val stations = mutableListOf<Station>()
        val bikes = mutableListOf<Bike>()
        tmp.forEach {
            val bikeNumbers = it.string("bike_numbers")?.split(',')
            val stationId = it.string("number").toIntOrZero()
            if (stationId != 0) {
                stations.add(Station(
                   stationId = it.string("number").toIntOrZero(),
                   name = it.string("name").toStringOrEmpty(),
                   freeRacks = it.string("free_racks").toIntOrZero()
                ))
                if (bikeNumbers != null) {
                    Log.e("COS", bikeNumbers.toString())
                    bikes.addAll(bikeNumbers.filter{ it != "" }.map {
                        Bike(bikeId = it.toInt(), stationId = stationId)
                    })
                }
            }
        }
        return Pair(stations, bikes)
    }

    fun insert(stations: List<Station>, bikes: List<Bike>, context: Context) : Unit {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
        db.clearAllTables()
        db.stationDAO().insertAll(*stations.toTypedArray())
        db.BikeDAO().insertAll(*bikes.toTypedArray())
    }

}
