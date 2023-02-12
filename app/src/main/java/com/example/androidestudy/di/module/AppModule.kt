package com.example.androidestudy.di.module

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_DEFAULT
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidestudy.R
import com.example.androidestudy.feature.main_screen.MainActivity
import com.example.androidestudy.feature.notification.presentation.receiver.MyReceiver
import com.example.androidestudy.feature.notification.presentation.receiver.ReplyReceiver
import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.data.repository.UserPostRepositoryImpl
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import com.example.androidestudy.feature.todoapp.data.local.TodoDatabase
import com.example.androidestudy.feature.todoapp.data.remote.api.TodoWeatherApi
import com.example.androidestudy.feature.todoapp.data.repository.WeatherLocationSettingsImpl
import com.example.androidestudy.feature.todoapp.domain.repository.WeatherLocationSettings
import com.example.androidestudy.feature.util.MY_ARG
import com.example.androidestudy.feature.util.MY_URI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Firebase

    // 認証関連のものはViewModelが破棄されたらどうすべき
    // スコープについて考える必要がある
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    // Retrofit

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                // status code, request, responseのログを出力する
                HttpLoggingInterceptor().apply {
                    // content-type/sub-typeの出力
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Provides
    @Singleton
    fun  provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserPostApi(
        client: OkHttpClient,
        moshi: Moshi
    ): UserPostApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(UserPostApi.BASE_URL)
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideUserPostRepository(userPostApi: UserPostApi): UserPostRepository {
        return UserPostRepositoryImpl(userPostApi = userPostApi)
    }

    // Notification
    @Provides
    @Singleton
    @DefaultNotificationCompatBuilder
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {

        val intent = Intent(context, MyReceiver::class.java).apply {
            putExtra("Message", "Action Clicked")
        }

        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }

        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            "$MY_URI/$MY_ARG=Coming from Main Screen.".toUri(),
            context,
            MainActivity::class.java
        )
        val clickPendingIntent: PendingIntent = android.app.TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, flag)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            flag
        )

        return NotificationCompat.Builder(context, "Notification Channel")
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Simple Notification")
            .setContentText("This is a Simple Notification")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(VISIBILITY_PRIVATE)
            .setPublicVersion(
                NotificationCompat.Builder(context, "Notification Channel")
                    .setContentTitle("Hiddenn")
                    .setContentText("Unlock to see the message")
                    .build()
            )
            .addAction(0, "Action", pendingIntent)
            .setContentIntent(clickPendingIntent)
    }

    @Provides
    @Singleton
    @SecondNotificationCompatBuilder
    fun provideSecondNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, "Second Notification Channel")
            .setSmallIcon(R.drawable.ic_notifications)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
    }

    @Provides
    @Singleton
    @ThirdNotificationCompatBuilder
    fun provideThirdNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE
        } else {
            0
        }

        val remoteInput = RemoteInput.Builder(RESULT_KEY)
            .setLabel("Enter here")
            .build()

        val replyIntent = Intent(context, ReplyReceiver::class.java)

        val replyPendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            replyIntent,
            flag
        )

        val replyAction = NotificationCompat.Action.Builder(
            0,
            "Reply",
            replyPendingIntent
        ).addRemoteInput(remoteInput).build()

        val person = Person.Builder().setName("Ryosuke").build()
        val notificationStyle = NotificationCompat.MessagingStyle(person)
            .addMessage("What's up??", System.currentTimeMillis(), person)

        return NotificationCompat.Builder(context, "Third Notification")
            .setSmallIcon(R.drawable.ic_notifications)
            .setPriority(PRIORITY_DEFAULT)
            .setOnlyAlertOnce(true)
            .setStyle(notificationStyle)
            .addAction(replyAction)
    }

    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Notification Channel",
                "Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val secondChannel = NotificationChannel(
                "Second Notification Channel",
                "Second Notification",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
            notificationManager.createNotificationChannel(secondChannel)
        }
        return notificationManager
    }


    @Provides
    @Singleton
    fun provideWeatherLocationSettings(
        @ApplicationContext context: Context
    ) : WeatherLocationSettings {
        return WeatherLocationSettingsImpl(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideTodoWeatherApi(
        client: OkHttpClient,
        moshi: Moshi
    ): TodoWeatherApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.open-meteo.com/")
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultNotificationCompatBuilder

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SecondNotificationCompatBuilder


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ThirdNotificationCompatBuilder

const val RESULT_KEY = "RESULT KEY"