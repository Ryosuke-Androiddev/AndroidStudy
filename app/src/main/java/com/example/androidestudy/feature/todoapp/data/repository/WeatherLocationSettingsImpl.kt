package com.example.androidestudy.feature.todoapp.data.repository

import android.content.Context
import androidx.datastore.dataStore
import com.example.androidestudy.feature.todoapp.data.repository.WeatherLocationSettingsImpl.Companion.DATA_STORE_FILE_NAME
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.domain.model.weather.LocationState
import com.example.androidestudy.feature.todoapp.domain.repository.WeatherLocationSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.userSettingsPreferencesStore by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = WeatherLocationSettings
)

class WeatherLocationSettingsImpl(
    context: Context
): WeatherLocationSettings {

    private val protoDataStore = context.userSettingsPreferencesStore

    companion object {
        const val DATA_STORE_FILE_NAME = "Proto_Data_Store"
    }

    override suspend fun setLocation(
        location: Location,
        latitude: Double,
        longitude: Double
    ) {
        protoDataStore.updateData {
            it.copy(
                location = location,
                latitude = latitude,
                longitude = longitude
            )
        }
    }

    override fun getLocation(): Flow<LocationState> {
        return protoDataStore.data
            .catch {
                LocationState.Failure
            }.map { weatherLocation ->
                LocationState.Success(weatherLocation = weatherLocation)
            }
    }
}