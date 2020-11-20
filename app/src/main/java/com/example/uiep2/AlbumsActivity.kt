package com.example.uiep2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView

class AlbumsActivity : AppCompatActivity() {
    var names = arrayOf("Shrek 1" , "Shrek 2" , "Shrek 3")
    var images = intArrayOf(R.drawable.shrek1 , R.drawable.shrek2 , R.drawable.shrek3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val gridView = findViewById<GridView>(R.id.gridView)
        val mainAdapter = MainAdapter(this , names , images)
        gridView.adapter = mainAdapter
        gridView.onItemClickListener = OnItemClickListener { _ , _ , position , id ->
            val intent = Intent(this , AlbumDetailsActivity::class.java)
            intent.putExtra("position" , names[position])
            startActivity(intent)
        }
    }
}