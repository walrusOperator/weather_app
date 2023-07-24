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
import com.example.weather_app.R
import com.example.weather_app.viewModels.CurrentConditionsViewModel
import dagger.hilt.android.AndroidEntryPoint

//private val forecastItems = listOf(
//    CurrentConditions(1675152000,1675152000, 1675198800, Forecast(72F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1675238400,1675238400, 1675285200, Forecast(65F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1675324800,1675324800, 1675371600, Forecast(45F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1675411200,1675411200, 1675458000, Forecast(76F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1675497600,1675497600, 1675544400, Forecast(77F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1675584000,1675584000, 1675544400, Forecast(66F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1675670400,1675670400, 1675717200, Forecast(54F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1675756800,1675756800, 1675803600, Forecast(54F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1675843200,1675843200, 1675890000, Forecast(80F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1675929600,1675929600, 1675976400, Forecast(72F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1676016000,1676016000, 1676062800, Forecast(75F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1676102400,1676102400, 1676149200, Forecast(32F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1676188800,1676188800, 1676235600, Forecast(85F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1676275200,1676275200, 1676322000, Forecast(72F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1676361600,1676361600, 1676408400, Forecast(72F, 65F, 80F), 1023F, 100),
//    CurrentConditions(1676448000,1676448000, 1676494800, Forecast(72F, 65F, 80F), 1023F, 100),
//)

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
//                        forecastItemList()
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
//    LaunchedEffect(Unit) {
//        viewModel.viewAppeared()
//    }
    Spacer(modifier = Modifier.height(60.dp))
    Column() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "${weatherData.value?.cityName}",
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
                Text(text = "Low ${weatherData.value?.conditions?.tempMin?.toInt()}°", style = textStyle)
                Text(text = "High ${weatherData.value?.conditions?.tempMax?.toInt()}°", style = textStyle)
                Text(text = "Humidity ${weatherData.value?.conditions?.humidity}%", style = textStyle)
                Text(text = "Pressure ${weatherData.value?.conditions?.pressure} hPa", style = textStyle)
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

