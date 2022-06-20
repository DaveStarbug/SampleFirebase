package com.star.fire1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.RemoteMessage
import com.star.modulefirebase2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(applicationContext)

        NotificationCenter.getInstance()

        NotificationCenter.ldToken.observe(this) {
            Log.i("DEBUG", "token is: $it")
        }

        NotificationCenter.ldMessage.observe(this) {
            Log.i(
                "DEBUG",
                "onMessageReceived: ${it?.data?.get("title")} - ${
                    it?.data?.get(
                        "body"
                    )
                }"
            )
            showNotification(it?.data?.get("title"), it?.data?.get("body"))
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showNotification(title: String?, message: String?) {

        var context = applicationContext

        val CHANNEL_ID = "MYCHANNEL"
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION_EVENT)
            .build()
        val notificationChannel =
            NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_HIGH)
        //val pendingIntent = PendingIntent.getActivity(applicationContext, 1, intent, 0)
        //Uri soundUri = RingtoneManager.getDefaultUri(Notification.DEFAULT_VIBRATE);
        notificationChannel.setSound(null, attributes)
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            //.setContentIntent(pendingIntent)
            //.addAction(R.drawable.ic_launcher_foreground, "OPEN", pendingIntent)
            .setChannelId(CHANNEL_ID)
            .setSilent(true)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(1, notification)
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(context, soundUri)
        r.play()
    }
}