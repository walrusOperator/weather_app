package com.example.weather_app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.models.ApiService
import com.example.weather_app.models.Forecast
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {
    private val _multiForecast: MutableLiveData<Forecast> = MutableLiveData()
    val multiForecast: LiveData<Forecast>
        get() = _multiForecast

    fun viewAppeared() = viewModelScope.launch {
        _multiForecast.value = apiService.getForecast()
    }
}