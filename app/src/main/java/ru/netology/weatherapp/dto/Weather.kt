package ru.netology.weatherapp.dto

data class Weather(
    var name:String = "",
    var lastUpdated:String = "",
    var temp:Int = 0,
    var isDay:Boolean = true,
    var windKph:Int = 0,
    var windMsek:Int = 0,
    var humidity:Int = 0,
    var cloud:Int = 0,
    var feelsLikeTemp:Int = 0,
    var visKm:Int = 0,
    var uv:Int = 0
)
