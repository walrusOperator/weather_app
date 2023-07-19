package com.example.weather_app.viewModel

import androidx.lifecycle.ViewModel
import com.example.weather_app.data.CurrentConditions
import com.example.weather_app.data.Forecast
import com.squareup.moshi.Json
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import com.example.weather_app.OpenWeatherMapApi

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor (private val api: OpenWeatherMapApi): ViewModel() {
    private val _currentConditions = Channel<CurrentConditions>()

    public val currentConditions: Flow<CurrentConditions> = _currentConditions.receiveAsFlow()

    fun fetchData() = runBlocking {
        val currentConditions = api.getCurrentConditions("55426")
        _currentConditions.trySend(currentConditions)
    }
}
