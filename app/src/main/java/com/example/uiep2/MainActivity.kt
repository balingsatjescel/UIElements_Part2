package com.example.uiep2
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.support.v7.app.AppCompatActivity

var selectedSong = arrayListOf<String>()
val productsArray = arrayOf(
    "I'm a Believer",
    "All Star",
    "I'm On My Way",
    "My Beloved Monster",
    "Hallelujah",
    "Accidentally in Love",
    "Holding Out for a Hero",
    "Livin' la Vida Loca",
    "Funkytown",
    "Changes",
    "What I've Done",
    "Final Showdown",
    "This Moment",
    "Do You Remember Rock 'n' Roll Radio?",
    "Live and Let Die"
)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productsArray)
        val productsListView = findViewById<ListView>(R.id.productsListView)
        productsListView.adapter = adapter
        registerForContextMenu(productsListView)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.long_press, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add_to_queue -> {
                Toast.makeText(this, "Added to Queues", Toast.LENGTH_SHORT).show()
                val info = item.menuInfo as AdapterContextMenuInfo
                selectedSong.add(productsArray[info.position])
                true
            }
            else-> super.onContextItemSelected(item)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.go_to_queue_page -> {
                val intent = Intent(this, QueuedSongsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.go_to_songs -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.go_to_albums -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }

    }
}