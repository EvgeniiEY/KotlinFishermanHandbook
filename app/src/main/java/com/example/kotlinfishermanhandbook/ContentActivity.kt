package com.example.kotlinfishermanhandbook

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContentActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_layout)

        val tvT = findViewById<TextView>(R.id.tvTitle)
        tvT.text = intent.getStringExtra("title")

        val tvC = findViewById<TextView>(R.id.tvContent)
        tvC.text = intent.getStringExtra("content")

        val imV = findViewById<ImageView>(R.id.im)
        imV.setImageResource((intent.getIntExtra("image", R.drawable.ic_fish)))
    }

}