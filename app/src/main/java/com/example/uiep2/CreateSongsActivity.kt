package com.example.uiep2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uiep2.models.Song

class CreateSongsActivity : AppCompatActivity() {
    lateinit var addSongButton: Button
    lateinit var songTitle: EditText
    lateinit var songAuthor: EditText
    lateinit var songAlbum: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_songs)
        val databaseHandler = SongsTableHandler(this)
        songTitle = findViewById(R.id.songTitle)
        songAuthor = findViewById(R.id.songAuthor)
        songAlbum = findViewById(R.id.songAlbum)

        addSongButton = findViewById(R.id.addSongButton)
        addSongButton.setOnClickListener {
            val title = songTitle.text.toString()
            val author = songAuthor.text.toString()
            val album = songAlbum.text.toString()

            val song = Song(title = title, author = author, album = album)
            if (databaseHandler.create(song)) {
                Toast.makeText(applicationContext, "Song was added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
            clearFields()
        }
    }

    fun clearFields() {
        songTitle.text.clear()
        songAuthor.text.clear()
        songAlbum.text.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.display_song, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.display_new_song -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}