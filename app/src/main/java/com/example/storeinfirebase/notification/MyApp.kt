package com.example.storeinfirebase.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat


class MyApp(var mCtx: Context?) {
    private val ID_SMALL_NOTIFICATION = 235
    private val NOTIFICATION_ID = 1094

    fun showSmallNotificationBundel(
        id: Int,
        title: String?,
        message: String?,
        intent: Bundle,
        icon:Int
    ) {
        val resultPendingIntent = PendingIntent.getActivity(
            mCtx,
            ID_SMALL_NOTIFICATION,
            Intent(mCtx,intent::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = "channel_id"
        val mBuilder = NotificationCompat.Builder(mCtx!!, channelId)
        val notification: Notification
        notification = mBuilder
            .setSmallIcon(icon)
            .setContentIntent(resultPendingIntent)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(false)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .build()


        val notificationManager =
            (mCtx!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setShowBadge(false)
            channel.description = message
            channel.enableLights(false)
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
        }


        notificationManager.notify(id, notification)
    }

    fun getNotificationId(): Int {
        return NOTIFICATION_ID
    }

    fun showSmallNotification(
        id: Int,
        title: String?,
        message: String?,
        intent: Intent?,
        icon:Int
    ) {
        val resultPendingIntent = PendingIntent.getActivity(
            mCtx,
            ID_SMALL_NOTIFICATION,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = "channel_id"
        val mBuilder = NotificationCompat.Builder(mCtx!!, channelId)
        val notification: Notification
        notification = mBuilder
            .setSmallIcon(icon)
            .setContentIntent(resultPendingIntent)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(false)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .build()


        val notificationManager =
            (mCtx!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setShowBadge(false)
            channel.description = message
            channel.enableLights(false)
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
        }


        notificationManager.notify(id, notification)
    }
}