package com.example.weather_app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.models.ApiService
import com.example.weather_app.models.CurrentConditions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _currentConditions: MutableLiveData<CurrentConditions> = MutableLiveData()
    val currentConditions: LiveData<CurrentConditions>
        get() = _currentConditions
    val userText: MutableLiveData<String> = MutableLiveData("55426")
    val invalidZipCode: MutableLiveData<Boolean> = MutableLiveData(false)
    fun viewAppeared(zipCode: String?= userText.value) = viewModelScope.launch {
        _currentConditions.value = apiService.getCurrentConditions(zipCode.toString())
    }

    fun checkValidZipCode(): Boolean {
        val currentInput = userText.value
        return if (
            (currentInput.isNullOrEmpty() || currentInput.length != 5) || (currentInput.any { !it.isDigit() }))
            false
        else {
            viewAppeared()
            true
        }
    }
}



