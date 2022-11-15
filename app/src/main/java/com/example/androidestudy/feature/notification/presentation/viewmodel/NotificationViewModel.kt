package com.example.androidestudy.feature.notification.presentation.viewmodel

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat
): ViewModel() {

    fun displaySimpleNotification() {
        notificationManager.notify(1, notificationBuilder.build())
    }
}