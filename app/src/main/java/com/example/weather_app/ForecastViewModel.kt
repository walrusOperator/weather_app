package com.example.weather_app

import androidx.lifecycle.ViewModel
import com.example.weather_app.OpenWeatherMapApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import com.example.weather_app.data.Forecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


@HiltViewModel
class ForecastViewModel @Inject constructor (private val api: OpenWeatherMapApi): ViewModel() {
    private val _forecast = Channel<Forecast>()

    public val forecast: Flow<Forecast> = _forecast.receiveAsFlow()

    fun fetchData() = runBlocking {
        val forecast = api.getForecast("55426")
        _forecast.trySend(forecast)
    }
}