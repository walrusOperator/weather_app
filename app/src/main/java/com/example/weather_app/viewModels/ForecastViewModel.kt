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
    private val _forecast: MutableLiveData<Forecast> = MutableLiveData()
    val forecast: LiveData<Forecast>
        get() = _forecast

    fun viewAppeared(zipCode: String) = viewModelScope.launch {
        _forecast.value = apiService.getForecast(zip = zipCode)
    }

    fun checkValidZipCode(currentInput: String): Boolean {
        return if (
            (currentInput.isNullOrEmpty() || currentInput.length != 5) || (currentInput.any { !it.isDigit() }))
            false
        else {
            viewAppeared(currentInput)
            true
        }
    }
}