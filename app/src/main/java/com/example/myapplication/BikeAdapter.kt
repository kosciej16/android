package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.models.Bike

class BikeAdapter(val context: Context, var col: ArrayList<Bike>) : BaseAdapter() {

    lateinit var copy: ArrayList<Bike>

    init {
        copy = ArrayList<Bike>()
        copy.addAll(col)
    }


    override fun getItem(pos: Int) = col.get(pos)

    override fun getItemId(pos: Int) = pos.toLong()

    override fun getCount() = col.size

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup) : View? {
        val view: View
        if (convertView == null) {
           view = LayoutInflater.from(context).inflate(R.layout.bike_item, parent, false)
        } else {
           view = convertView
        }
        val item = getItem(pos)
        val bike = view.findViewById<TextView>(R.id.bike)
        bike.setText(item.bikeId.toString())
        val ring = view.findViewById<TextView>(R.id.ring)
        ring.setText(item.ring.toString())
        return view
    }

    fun filter(text: String) {
        col.clear()
        if (text?.length != 0) {
            col.addAll(copy.filter { it.bikeId.toString().startsWith(text) })
        }
        notifyDataSetChanged()
    }
}