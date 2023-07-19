package com.example.weather_app.data

import com.squareup.moshi.Json

data class CurrentConditions (
    @Json(name = "dt") val date : Long,
    @Json(name = "sunrise") val sunrise : Long,
    @Json(name = "sunset") val sunset : Long,
    @Json(name = "temp") val temp : Forecast,
    @Json(name = "pressure") val pressure : Float,
    @Json(name = "humidity") val humidity : Int
)

