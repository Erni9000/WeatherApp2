package com.example.weatherapp2.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.weatherapp2.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collect

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val clickingWeather: LinearLayout = binding.dbWeatheritem
        clickingWeather.isVisible = false

        val displayWeatherIcon: ImageView = binding.dbWeathericon
        val displayTemperature: TextView = binding.dbDisplaylocaltemperature
        val displayTemperaturLike: TextView = binding.dbTempFeelsLike
        val displayLocation: TextView = binding.dbLocation
        val displayWeather: TextView = binding.dbWeatherdescription
        val inputLocation: EditText = binding.dbSearchinput


        inputLocation.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                searchViewModel.updateWeather(inputLocation.text.toString())
                clickingWeather.isVisible = true
            }
            false
        })


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.weatherByApi.collect {
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
                            SearchFragmentDirections.actionNavigationDashboardToHomeFragmentAdvanced(
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