package com.example.weatherapp2.ui.local


import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class WeatherByApi(
    var coord: Coord = Coord(),
    var weather: List<Weather> = listOf(),
    var base: String = " ",
    var main: Main = Main(),
    var visibility: Long = 10000,
    var wind: Wind = Wind(),
    var clouds: Clouds = Clouds(),
    var rain: Rain? = null,
    var snow: Snow? = null,
    var dt: Long = 0,
    var sys: Sys = Sys(),
    var timezone: Long = 7200,
    var name: String = "GG WP",
    var cod: Long = 0
) : Serializable

data class Coord(
    var lon: Double = 0.0,
    var lat: Double = 0.0
)

data class Weather(
    var id: Int = 0,
    var main: String = "Clear",
    var description: String = "broken arms",
    var icon: String = "01d"
)

data class Main(
    var temp: Double = -1000000000000.0,
    var feels_like: Double = -100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0,
    var temp_min: Double = -1.0,
    var temp_max: Double = -1.0,
    var pressure: Long = 0,
    var humidity: Int = -1
)

data class Wind(
    var speed: Double = 0.0,
    var deg: Int = 0,
    var gust: Double = 0.0
)

data class Clouds(
    var all: Long = 0
)

data class Rain(
    var `1h`: Double = 0.0,
)

data class Snow(
    var `1h`: Double = 0.0
)

data class Sys(
    var type: Long = 0,
    var country: String = "Better luck next time ",
    var sunrise: Long = 0,
    var sunset: Long = 0

)