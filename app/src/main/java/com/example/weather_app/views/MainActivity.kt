package com.example.weather_app.views
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.ui.theme.Weather_appTheme
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.weather_app.R
import com.example.weather_app.viewModels.CurrentConditionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
                        forecastItemList()
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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
fun ShowWeather(navController : NavController, viewModel: CurrentConditionsViewModel = hiltViewModel()) {
    val weatherData = viewModel.currentConditions.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.viewAppeared()
    }
    Spacer(modifier = Modifier.height(60.dp))
    Column() {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${weatherData.value?.cityName}",
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
                        text = "${weatherData.value?.conditions?.temp?.toInt()}°",
                        style = TextStyle(
                            fontWeight = FontWeight(400),
                            fontSize = 72.sp
                        )
                    )
                    Text(
                        text = "Feels like ${weatherData.value?.conditions?.feelsLike?.toInt()}°",
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(1f, fill = true))
                AsyncImage(model = "https://openweathermap.org/img/wn/${weatherData.value?.weatherData?.get(0)?.iconName}@2x.png",
                    contentDescription = "${weatherData.value?.weatherData?.get(0)?.description}@2x.png",
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
                Text(
                    text = "Low ${weatherData.value?.conditions?.tempMin?.toInt()}°",
                    style = textStyle
                )
                Text(
                    text = "High ${weatherData.value?.conditions?.tempMax?.toInt()}°",
                    style = textStyle
                )
                Text(
                    text = "Humidity ${weatherData.value?.conditions?.humidity}%",
                    style = textStyle
                )
                Text(
                    text = "Pressure ${weatherData.value?.conditions?.pressure} hPa",
                    style = textStyle
                )
            }
            Spacer(modifier = Modifier.height(65.dp))
            Button(
                onClick = { navController.navigate("ForecastScreen") },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Blue)
            )
            {
                Text(
                    text = "Forecast",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

        }
    }
}

