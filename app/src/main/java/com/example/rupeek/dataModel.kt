package com.example.rupeek

//import com.google.gson.annotations.SerializedName

/*
data class WeatherData (
    */
/*
    * "temp":24,
"time":1564012800,
"rain":40,
"wind":15*//*

    @SerializedName("temp") val temp:Int?,
    @SerializedName("time") val time:Long?,
    @SerializedName("rain") val rain:Int?,
    @SerializedName("wind") val wind:Int?,
)

data class WeatherResponse(
    @SerializedName("data") val weatherData: WeatherData
)*/
