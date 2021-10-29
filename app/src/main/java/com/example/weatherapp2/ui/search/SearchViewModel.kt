package com.example.weatherapp2.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp2.ui.local.Weather
import com.example.weatherapp2.ui.local.WeatherByApi
import com.example.weatherapp2.ui.local.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SearchViewModel : ViewModel() {

    val weatherByApi = MutableStateFlow(WeatherByApi())

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service: WeatherService = retrofit.create(WeatherService::class.java)
    fun updateWeather(search: String) {
        viewModelScope.launch {
            val response =
                service.getWeatherBySearch(search)
            println(response.isSuccessful)
            if (response.isSuccessful) {
                weatherByApi.value = response.body()!!
                println(response.body())


            } else {
                weatherByApi.value =
                    WeatherByApi(weather = listOf(Weather(description = "404 - Not found")))
            }

        }
    }
}