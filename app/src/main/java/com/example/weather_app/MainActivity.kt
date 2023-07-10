package com.example.weather_app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.ui.theme.Weather_appTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weather_app.data.DayForecast
import com.example.weather_app.data.ForecastTemp
import com.example.weather_app.forecastItemList




private val forecastItems = listOf(
    DayForecast(31,8L, 21L, ForecastTemp(72F, 65F, 80F), 1023F, 100),
    DayForecast(31-1,12L, 21L, ForecastTemp(72F, 65F, 80F), 1023F, 100)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Weather_appTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Start") {
                    composable(route = "Start") {
                        MyCurrentWeather(navController)
                    }
                    composable("ForecastScreen") {
                        forecastItemList(dataItems = forecastItems, navController = navController)

                    }
                    }
                }
        }
    }
}

@Composable
fun MyCurrentWeather(navController : NavController) {
    TopBar()
    Column{
        ShowWeather(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "My Weather App", color = Color.White)},
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowWeather(navController : NavController) {
    Spacer(modifier = Modifier.height(60.dp))
    Column() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.city),
                fontSize = 22.sp,
                fontWeight = FontWeight(500)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.temp),
                    style = TextStyle(
                        fontWeight = FontWeight(400),
                        fontSize = 72.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.feels_like),
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f, fill = true))
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Clear sun",
                modifier = Modifier
                    .size(size = 100.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                val textStyle = TextStyle(
                    fontSize = 18.sp
                )
                Text(text = stringResource(id = R.string.low), style = textStyle)
                Text(text = stringResource(id = R.string.high), style = textStyle)
                Text(text = stringResource(id = R.string.humidity), style = textStyle)
                Text(text = stringResource(id = R.string.pressure), style = textStyle)

            }
            
            Spacer(modifier = Modifier.height(65.dp))
            Button(onClick = {navController.navigate("ForecastScreen")},
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Blue))
            {
                Text(text = "Forecast",
                fontSize = 18.sp,
                color = Color.White)
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Weather_appTheme {
        Greeting("Android")
    }
}

//@Composable
//fun ForecastItemList(dataItems: List<DayForecast>, navController : NavController) {
//    /* Create the list here. This function will call DataItemView() */
//    LazyColumn {
//        for(data in dataItems){
//            item{ForecastItemView(data, navController = navController)}
//        }
//    }
//}
//
//@Composable
//fun ForecastItemView(dataItem: DayForecast, navController : NavController) {
//    /* Create the view for the data item her. */
////    Row (
////        Modifier.padding(bottom = 8.dp)
////            .clickable(onClick = {
////                navController.navigate(route = buildString {
////                    append("DetailsScreen/")
////                    append(dataItem.id)
////                })
////            })){
////        Text(text = dataItem.id.toString())
////        Spacer(modifier = Modifier.size(8.dp))
////
////        Column {
////            Text(text = dataItem.name, style = TextStyle(fontWeight = FontWeight.Bold))
////            Text(text = dataItem.description, style = TextStyle(fontSize = 12.sp))
////        }
////    }
//
//}

