package com.example.weather_app.models

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("weather")

    suspend fun getCurrentConditions(
        @Query(value = "zip") zip: String = "55426,us",
        @Query(value = "units") units: String = "imperial",
        @Query(value = "appid") appid: String = "9d64bb2a651b234eef2d08492ac7478a"
    ) : CurrentConditions

    @GET("forecast/daily")
    suspend fun getForecast(
        @Query(value = "zip") zip: String = "55119,us",
        @Query(value = "units") units: String = "imperial",
        @Query(value = "cnt") count: Int = 16,
        @Query(value = "appid") appID: String = "9d64bb2a651b234eef2d08492ac7478a"
    ) : Forecast

}