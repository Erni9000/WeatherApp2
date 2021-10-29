package com.example.weatherapp2.ui.local

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.weatherapp2.databinding.FragmentLocalBinding
import kotlinx.coroutines.flow.collect

class LocalFragment : Fragment() {


    private lateinit var localViewModel: LocalViewModel
    private var _binding: FragmentLocalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        localViewModel =
            ViewModelProvider(this).get(LocalViewModel::class.java)
        _binding = FragmentLocalBinding.inflate(inflater, container, false)

        localViewModel.isLocationPermissionGranted(this.context!!, this.activity!!)
        localViewModel.setupLocation(this.context!!)
        localViewModel.updateWeather()
        localViewModel.getLocation()

        val root: View = binding.root
        val clickingWeather: LinearLayout = binding.weatheritem

        val displayWeatherIcon: ImageView = binding.weathericon
        val displayTemperature: TextView = binding.displaylocaltemperature
        val displayTemperaturLike: TextView = binding.tempFeelsLike
        val displayLocation: TextView = binding.location
        val displayWeather: TextView = binding.weatherdescription
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            localViewModel.weatherByApi.collect {
                if (!it.weather.isEmpty()) {
                    Glide.with(displayWeatherIcon)
                        .load("http://openweathermap.org/img/wn/${it.weather.first().icon}@4x.png")
                        .into(displayWeatherIcon)
                    displayWeather.text = it.weather.first().description
                    displayLocation.text = it.name + ", " + it.sys.country
                    displayTemperature.text = "Temperatur:" + it.main.temp.toString() + "°C"
                    displayTemperaturLike.text =
                        "Gefühlt wie: " + it.main.feels_like.toString() + "°C"
                    clickingWeather.setOnClickListener { _ ->
                        findNavController().navigate(
                            LocalFragmentDirections.actionNavigationLocalToHomeFragmentAdvanced(
                                it
                            )
                        )
                    }

                }
            }
        }



        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}