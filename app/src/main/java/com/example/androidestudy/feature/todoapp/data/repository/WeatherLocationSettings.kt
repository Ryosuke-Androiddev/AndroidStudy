package com.example.androidestudy.feature.todoapp.data.repository

import androidx.datastore.core.Serializer
import com.example.androidestudy.feature.todoapp.domain.model.weather.WeatherLocation
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object WeatherLocationSettings: Serializer<WeatherLocation>{

    override val defaultValue: WeatherLocation
        get() = WeatherLocation()

    override suspend fun readFrom(input: InputStream): WeatherLocation {
        return try {
            Json.decodeFromString(
                deserializer = WeatherLocation.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: WeatherLocation, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = WeatherLocation.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}