package com.example.weather_app.data

import com.squareup.moshi.Json

data class Forecast(
    @Json(name = "day") val day : Float,
    @Json(name = "min") val min : Float,
    @Json(name = "max") val max : Float
)