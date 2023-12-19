package ru.netology.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.weatherapp.dto.dataWeather
import ru.netology.weatherapp.weather.RepositoryImpl
import java.lang.Exception
import java.lang.RuntimeException

class WeatherViewModel : ViewModel() {

    var city: String = "Kazan"
    val weather = MutableLiveData<dataWeather>()
  //  var weather: dataWeather? = null
    val repository: RepositoryImpl = RepositoryImpl()

    fun getWeather(key: String) {
        viewModelScope.launch {
            try {
                weather.value = repository.getWeather(key, city)

            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}