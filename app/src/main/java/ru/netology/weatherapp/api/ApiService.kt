package ru.netology.weatherapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.weatherapp.dto.dataWeather

// https://api.weatherapi.com/v1/current.json?key=a91fd16323ea4aa995e63153230908 &q=Kazan&aqi=no

private const val BASE_URL = "https://api.weatherapi.com/v1/"

private const val API_KEY = "a91fd16323ea4aa995e63153230908"

private val logging = HttpLoggingInterceptor().apply {
     level = HttpLoggingInterceptor.Level.BODY

}

private val okhttp = OkHttpClient.Builder()
//    .addInterceptor { chain ->
//        AppAuth.getInstance().authStateFlow.value.token?.let { token ->
//            val newRequest = chain.request().newBuilder()
//                .addHeader("Authorization", token)
//                .build()
//            return@addInterceptor chain.proceed(newRequest)
//        }
//        chain.proceed(chain.request())
//    }
    .addInterceptor(logging)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okhttp)
    .build()

interface ApiService {
    @GET("current.json")
    suspend fun getWeather(@Query("key") key:String=API_KEY, @Query("q") city:String, @Query("aqi") no:String="no") :Response<dataWeather>
}

object Api {
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}