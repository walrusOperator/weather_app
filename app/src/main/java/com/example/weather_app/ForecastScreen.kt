package com.example.weather_app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.weather_app.data.DayForecast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun forecastItemList(dataItems: List<DayForecast>, navController : NavController) {
        /* Create the list here. This function will call DataItemView() */
    TopAppBar(
        title = {
            Text(text = "Forecast", color = Color.White)},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
    )
    Column {
        Spacer(modifier = Modifier.padding(top = 60.dp))
        LazyColumn {
            for (data in dataItems) {
                item { forecastItemView(data, navController = navController) }
            }
        }
    }
    }


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun forecastItemView(dataItem: DayForecast, navController : NavController) {
    /* Create the view for the data item her. */
    Row(
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.sun),
            contentDescription = "Sunny Weather Image",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.weight(.25f, fill = true))
        Text(
            text = dateConvert(dataItem.date),
            style = TextStyle(
                fontSize = 20.sp,
            )
        )
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Column {
            Text(
                text = "Temp: ${dataItem.temp.day}º",
                fontSize = 16.sp
            )
            Text(
                text = "High: ${dataItem.temp.max}º",
                fontSize = 16.sp
            )
        }
            Spacer(modifier = Modifier.weight(1f, fill = true))
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Sunrise: ${timeConvert(dataItem.sunrise)}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Sunset: ${timeConvert(dataItem.sunset)}",
                    fontSize = 16.sp
                )
            }
        }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun timeConvert(timeStamp : Long) : String {
    val tempTime = Instant.ofEpochMilli(timeStamp)
    val dateTime = LocalDateTime.ofInstant(tempTime, ZoneOffset.UTC)
    val formatter = DateTimeFormatter.ofPattern("HH:mm a")
    return dateTime.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun dateConvert(date : Long) : String {
    val tempDate = Instant.ofEpochSecond(date)
    val dateTime = LocalDateTime.ofInstant(tempDate, ZoneOffset.UTC)
    val formatter = DateTimeFormatter.ofPattern("MMM dd")
    return dateTime.format(formatter)
}

