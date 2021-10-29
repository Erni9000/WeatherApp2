package com.example.weatherapp2.ui.local.advancedInfo

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class AdvancedInfoViewModel : ViewModel() {

    fun getDateTime(l: Long): String? {
        val sdf = SimpleDateFormat("HH:mm:ss")
        return sdf.format(Date(l * 1000))
    }
}