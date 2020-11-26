package com.example.uiep2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*


class QueuedSongsActivity : AppCompatActivity() {
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notification"
    private val description = "Test Notification"
    private val adapter : ArrayAdapter<String>? = null


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.queue_delete_song, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, selectedSong)
        val queueListView = findViewById<ListView>(R.id.queueListView)
        queueListView.adapter = adapter
        registerForContextMenu(queueListView)

        if(queueListView.adapter?.count == 0){
            val intent = Intent(applicationContext, QueuedSongsActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(applicationContext, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                notificationChannel = NotificationChannel(
                    channelId,description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this,channelId)
                    .setContentTitle("Notification Example")
                    .setContentText("This is a notification message")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent)
            } else {
                builder = Notification.Builder(this)
                    .setContentTitle("Notification Example")
                    .setContentText("This is a notification message")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234,builder.build())
            val toast = Toast.makeText(applicationContext, "Empty List", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.queue_delete_song, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.song_delete -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                selectedSong.removeAt(info.position)
                adapter?.notifyDataSetChanged()
                startActivity(Intent(this, QueuedSongsActivity::class.java))
                val toast = Toast.makeText(this, "Song removed from the list", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return super.onContextItemSelected(item)
    }
}