package com.example.uiep2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class QueuedSongsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, selectedSong)
        val queueListView = findViewById<ListView>(R.id.queueListView)
        queueListView.adapter = adapter
    }
}