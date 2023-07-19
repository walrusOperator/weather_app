package com.example.weather_app

import com.example.weather_app.data.CurrentConditions
import com.example.weather_app.data.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "c2a337bdfc23e2f15dbd73d206138390",
        @Query("units") units: String = "imperial"
    ) : CurrentConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getForecast(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "c2a337bdfc23e2f15dbd73d206138390",
        @Query("cnt") numDays: Int = 16,
        @Query("units") units: String = "imperial"
    ) : Forecast
}