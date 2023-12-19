package ru.netology.weatherapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.toolbox.Volley
import ru.netology.weatherapp.R
import ru.netology.weatherapp.databinding.ActivityMainBinding
import ru.netology.weatherapp.viewmodel.WeatherViewModel
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.netology.weatherapp.api.Api


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: WeatherViewModel by viewModels()

    private val API_KEY = "a91fd16323ea4aa995e63153230908"
    //  var city: String = "Kazan"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.weather.observe(this) {
            binding.apply {
                nameCity.text = viewModel.city
                localTime.text = it.location.localtime

                with(it.current) {
                    temp.text = this.temp_c.toInt().toString()
                    Glide.with(imageWeather)
                        .load(this.condition.icon)
                        .into(imageWeather)
                    wind.text =
                        "Скорость ветра ${this.wind_kph} км/ч (${(this.wind_kph * 1000 / 3600).toInt()} м/с)"
                    humitidy.text = "Влажность ${this.humidity} %"
                    cloudy.text = "Облачность ${this.cloud} %"
                    feelsTemp.text = "Ощущается как ${this.feelslike_c.toInt()}"
                    visKm.text = "Видимость ${this.vis_km} км"
                    uvIndex.text = "УФ-индекс ${this.uv}"
                    lastUpdated.text = "данные обновлены: ${this.last_updated}"
                }
            }
        }

        binding.bGet.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getWeather(API_KEY)
            }
        }
//                runOnUiThread {
//                    viewModel.weather.value?.let {
//                        binding.apply {
//                            nameCity.text = viewModel.city
//                            localTime.text = it.location.localtime
//
//                            with(it.current) {
//                                temp.text = this.temp_c.toInt().toString()
//                                Glide.with(imageWeather)
//                                    .load(this.condition.icon)
//                                    .into(imageWeather)
//                                wind.text =
//                                    "Скорость ветра ${this.wind_kph} км/ч (${this.wind_kph * 1000 / 3600} м/с)"
//                                humitidy.text = "Влажность ${this.humidity} %"
//                                cloudy.text = "Облачность ${this.cloud} %"
//                                feelsTemp.text = "Ощущается как ${this.feelslike_c.toInt()}"
//                                visKm.text = "Видимость ${this.vis_km} км"
//                                uvIndex.text = "УФ-индекс ${this.uv}"
//                                lastUpdated.text = "данные обновлены: ${this.last_updated}"
//                            }
//                        }
//                    }
//                }
    }
}


//            val queue = Volley.newRequestQueue(this)
//            getResult(queue, city)
//            binding.apply {
//                wind.text = "Скорость ветра ${kazanInfo.windKph} км/ч (${kazanInfo.windMsek} м/с)"
//                temp.text = kazanInfo.temp.toString()
//                humitidy.text = "Влажность ${kazanInfo.humidity} %"
//                DayOrNight.setImageResource(if (kazanInfo.isDay) R.drawable.day113 else R.drawable.night113)
//                cloudy.text = "Облачность ${kazanInfo.cloud} %"
//                feelsTemp.text = "Ощущается как ${kazanInfo.feelsLikeTemp}"
//                visKm.text = "Видимость ${kazanInfo.visKm} км"
//                uv.text = "УФ-индекс ${kazanInfo.uv}"
//                lastUpdated.text = "данные обновлены: ${kazanInfo.lastUpdated}"
//            }
//        }

