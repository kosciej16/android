package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.activities.StationActivity
import com.example.myapplication.models.Station
import java.util.*

class StationAdapter(val context: Context, var col: ArrayList<Station>) : BaseAdapter() {

    var copy: ArrayList<Station>

    init {
        copy = ArrayList<Station>()
        copy.addAll(col)
        col.clear()
    }


    override fun getItem(pos: Int) = col.get(pos)

    override fun getItemId(pos: Int) = pos.toLong()

    override fun getCount() = col.size

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup) : View? {
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.station_item, parent, false)
        } else {
            view = convertView
        }
        val item = getItem(pos)
        val number = view.findViewById<TextView>(R.id.station_number)
        number.setText(item.stationId.toString())
        val name = view.findViewById<TextView>(R.id.station_name)
        name.setText(item.name)
        val free = view.findViewById<TextView>(R.id.free_racks)
        free.setText(item.freeRacks.toString())
        view.setOnClickListener() {
            val intent = Intent(context, StationActivity::class.java)
            intent.putExtra("your_extra",item.stationId)
            context.startActivity(intent)
        }
        return view
    }

    fun filter(text: String) {
        col.clear()
        if (text.length != 0) {
            col.addAll(copy.filter { it.name.toLowerCase().startsWith(text, ignoreCase = true) })
        }
        notifyDataSetChanged()
    }
}
