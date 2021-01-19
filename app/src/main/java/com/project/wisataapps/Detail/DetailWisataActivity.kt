package com.project.wisataapps.Detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.project.wisataapps.R
import org.w3c.dom.Text

class DetailWisataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_wisata)
        setSupportActionBar(findViewById(R.id.toolbar))

        val nama = intent.getStringExtra("jdl")
        val desc = intent.getStringExtra("desc")
        val img = intent.getStringExtra("img")

        val jdltv = findViewById<TextView>(R.id.jdlDetail)
        val desctv = findViewById<TextView>(R.id.descDetail)
        val imgDet = findViewById<ImageView>(R.id.imgDetail)

        jdltv.text = nama
        desctv.text = desc
        Glide.with(this).load(img).into(imgDet)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}