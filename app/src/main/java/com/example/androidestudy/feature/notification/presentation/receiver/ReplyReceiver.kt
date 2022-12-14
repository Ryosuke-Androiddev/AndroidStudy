package com.example.androidestudy.feature.notification.presentation.receiver

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import com.example.androidestudy.di.module.RESULT_KEY
import com.example.androidestudy.di.module.ThirdNotificationCompatBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReplyReceiver: BroadcastReceiver() {

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    @Inject
    @ThirdNotificationCompatBuilder
    lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onReceive(context: Context?, intent: Intent?) {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null) {
            val input = remoteInput.getCharSequence(RESULT_KEY).toString()
            val person = Person.Builder().setName("Me").build()
            val message = NotificationCompat.MessagingStyle.Message(
                input, System.currentTimeMillis(), person
            )
            val notificationStyle = NotificationCompat.MessagingStyle(person).addMessage(message)
            notificationManager.notify(
                1,
                notificationBuilder
//                    .setStyle(notificationStyle)
                    .setContentTitle("Sent!")
                    .setStyle(null)
                    .build()
            )
        }
    }
}