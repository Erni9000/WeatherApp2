package com.example.weatherapp2.ui.local.advancedInfo


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.weatherapp2.R
import com.example.weatherapp2.databinding.FragmentInfoAdvancedBinding


class AdvancedInfoFragment : Fragment() {

    private var _binding: FragmentInfoAdvancedBinding? = null
    private lateinit var advancedInfoViewModel: AdvancedInfoViewModel
    private val args by navArgs<AdvancedInfoFragmentArgs>()
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        advancedInfoViewModel = ViewModelProvider(this).get(AdvancedInfoViewModel::class.java)
        _binding = FragmentInfoAdvancedBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //Variablen zu Textview zuweisen
        val displayWeatherIcon: ImageView = binding.weathericonadvanced
        val displayTemperature: TextView = binding.displaylocaltemperatureadvanced
        val displayTemperatureLike: TextView = binding.tempFeelsLikeadvanced
        val displayTemperatureMax: TextView = binding.tempMaxadvanced
        val displayTemperatureMin: TextView = binding.tempMinadvanced
        val displayPressure: TextView = binding.pressureadvanced
        val displayHumidity: TextView = binding.humidityadvanced
        val displayWindSpeed: TextView = binding.windspeedadvanced
        val displayPrecipitation: TextView = binding.rainorsnowperonehouradvanced
        val displaySunRise: TextView = binding.sunriseadvanced
        val displaySunSet: TextView = binding.sunsetadvanced
        val displayLocation: TextView = binding.locationadvanced
        val displayVisibility: TextView = binding.visibilityadvanced
        val displayWeather: TextView = binding.weatherdescriptionadvanced
        val displayCompass: ImageView = binding.compassadvanced

        val weatheradvanced = args.weatherInfo

        //Füllen des Layouts
        Glide.with(displayWeatherIcon)
            .load(
                "http://openweathermap.org/img/wn/${
                    weatheradvanced
                        .weather.first().icon
                }@4x.png"
            )
            .into(displayWeatherIcon)
        displayWeather.text = weatheradvanced.weather.first().description
        displayTemperatureMax.text = "Maximale Temperatur (heute): " +
                weatheradvanced.main.temp_max.toString() + "°C"
        displayTemperatureMin.text = "Minimale Temperatur (heute): " +
                weatheradvanced.main.temp_min.toString() + "°C"
        displayPressure.text = "Luftdruck: " +
                weatheradvanced.main.pressure.toString() + "hPa"
        displayHumidity.text = "Luftfeuchtigkeit: " +
                weatheradvanced.main.humidity.toString() + "%"
        displayLocation.text = weatheradvanced.name + ", " +
                weatheradvanced.sys.country
        displayWindSpeed.text = "Windgeschwindigkeit: " + weatheradvanced.wind.speed.toString() +
                "m/s"
        displayTemperature.text = "Temperatur:" +
                weatheradvanced.main.temp.toString() + "°C"
        displayTemperatureLike.text = "Gefühlt wie: " +
                weatheradvanced.main.feels_like.toString() + "°C"
        if (weatheradvanced.rain == null && weatheradvanced.snow == null) {
            displayPrecipitation.isVisible = false
        } else if (weatheradvanced.rain != null) {
            displayPrecipitation.text = "Regen: " + weatheradvanced.rain!!.`1h` + "mm/h"
        } else {
            displayPrecipitation.text = "Schneefall: " + weatheradvanced.snow!!.`1h` + "mm/h"
        }
        displaySunRise.text = "Sonnenaufgang: " +
                advancedInfoViewModel
                    .getDateTime(weatheradvanced.sys.sunrise + weatheradvanced.timezone - 3600) + "Uhr"
        displaySunSet.text = "Sonnenuntergang: " +
                advancedInfoViewModel
                    .getDateTime(weatheradvanced.sys.sunset + weatheradvanced.timezone - 3600) + "Uhr"
        displayVisibility.text = "Sichtweite: " + weatheradvanced.visibility.toString() + "m"

        displayCompass.setImageResource(R.drawable.preloadercompassneedle)
        displayCompass.rotation = weatheradvanced.wind.deg.toFloat()
        return root
    }
}