package ru.netology.weatherapp.weather

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import ru.netology.weatherapp.api.Api
import ru.netology.weatherapp.dto.dataWeather
import java.lang.RuntimeException

class RepositoryImpl {

    suspend fun getWeather(key:String, city:String):dataWeather {

        try {
            val response = Api.service.getWeather(key, city)
            if (!response.isSuccessful) {

                throw RuntimeException(response.message())
            }
            Log.d("MyLog", "${response.body()}")
            return response.body() ?: throw RuntimeException(response.message())

        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }


    }
//    private val API_KEY = "a91fd16323ea4aa995e63153230908"

//    var kazanInfo: Info = Info(name = "Kазань")
//
//    fun extractAndFillDateFromJson(obj: JSONObject) {
//        val currentObj = obj.getJSONObject("current")
//        kazanInfo.temp = currentObj.getInt("temp_c")
//        kazanInfo.isDay = currentObj.getInt("is_day") == 1
//        kazanInfo.windKph = currentObj.getInt("wind_kph")
//        kazanInfo.windMsek = kazanInfo.windKph * 1000 / 3600
//        kazanInfo.humidity = currentObj.getInt("humidity")
//        kazanInfo.cloud = currentObj.getInt("cloud")
//        kazanInfo.feelsLikeTemp = currentObj.getInt("feelslike_c")
//        kazanInfo.visKm = currentObj.getInt("vis_km")
//        kazanInfo.uv = currentObj.getInt("uv")
//        kazanInfo.lastUpdated = currentObj.getString("last_updated")
//    }

//    fun getResult(queue: RequestQueue, name: String) {
//
//        val url = "https://api.weatherapi.com/v1/current.json" +
//                "?key=$API_KEY&q=$name&aqi=no"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET,
//            url,
//            { response ->
//                val obj = JSONObject(response)
//                extractAndFillDateFromJson(obj)
//            },
//            {
//                Log.d("MyLog", "Volley error: $it")
//            }
//        )
//        queue.add(stringRequest)
//    }
}




