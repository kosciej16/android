package com.example.myapplication.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class AddBikeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_ring)
    }

    fun addRingNumber(view : View) {
        val bike = findViewById<EditText>(R.id.bikeNumber).editableText.toString()
        val ring = findViewById<EditText>(R.id.ringNumber).editableText.toString()
        val prefs = this.getSharedPreferences("bikes",Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(bike, ring)
        editor.apply()
        finish()

    }

}
