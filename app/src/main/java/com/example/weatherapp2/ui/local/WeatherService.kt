package com.example.weatherapp2.ui.local

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("/data/2.5/weather?units=metric&lang=de&appid=c48d2c9ed72322d83f032499f4ef93ae")
    suspend fun getWeatherByGps(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<WeatherByApi>

    //https://api.openweathermap.org/data/2.5/weather?q=Leipzig,%20DE&appid=c48d2c9ed72322d83f032499f4ef93ae
    @GET("/data/2.5/weather?&lang=de&units=metric&appid=c48d2c9ed72322d83f032499f4ef93ae")
    suspend fun getWeatherBySearch(
        @Query("q") search: String
    ): Response<WeatherByApi>
}