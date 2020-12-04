package com.example.uiep2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uiep2.models.Song

class EditSongActivity : AppCompatActivity() {
    lateinit var editSongButton: Button
    lateinit var editSongTitle: EditText
    lateinit var editSongAuthor: EditText
    lateinit var editSongAlbum: EditText
    lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_song)

        val song_id = intent.getIntExtra("song_id", 0)

        val databaseHandler = SongsTableHandler(this)
        song = databaseHandler.readOne(song_id)

        editSongTitle = findViewById(R.id.editSongTitle)
        editSongAuthor = findViewById(R.id.editSongAuthor)
        editSongAlbum = findViewById(R.id.editSongAlbum)
        editSongButton = findViewById(R.id.editSongButton)

        editSongTitle.setText(song.title)
        editSongAuthor.setText(song.author)
        editSongAlbum.setText(song.album)

        editSongButton.setOnClickListener{
            val title = editSongTitle.text.toString()
            val author = editSongAuthor.text.toString()
            val album = editSongAlbum.text.toString()

            val updated_song = Song(id = song.id, title = title, author = author, album = album)
            if (databaseHandler.update(updated_song)) {
                Toast.makeText(applicationContext, "Song was updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}