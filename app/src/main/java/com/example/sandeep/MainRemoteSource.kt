package com.example.sandeep

import android.util.Log
import com.example.core.di.FormattedResponse
import com.example.core.util.GenericApiResponse
import com.example.core.util.Resource
import javax.inject.Inject

/**
 * Created by Sandeep Sankla
 */
class MainRemoteSource @Inject constructor(val formattedResponse: FormattedResponse)   {

    suspend fun getWeatherDetails(): Resource<GenericApiResponse<User>> {

        val url = "https://user-service.parkwheels.co.in/api/v1/user/profile/"
       // val url = "http://api.weatherstack.com/current?access_key=313510887793bf027cb42a57e9d43d42&query=New York"
        Log.d("sasa", "in main source")
        val datares : Resource<GenericApiResponse<User>>  = formattedResponse.GetCall(url, hashMapOf())
        Log.d("sasa", "$datares")
        return datares
    }
   suspend fun getWeatherDetail(): Resource<Weather> {

       // val url = "https://user-service.parkwheels.co.in/api/v1/user/profile/"
        val url = "http://api.weatherstack.com/current?access_key=313510887793bf027cb42a57e9d43d42&query=New York"
        Log.d("sasa", "in main source")
        val datares : Resource<Weather>  = formattedResponse.GetCall(
            url,
            hashMapOf()
        )
        Log.d("sasa", "$datares")
        return datares
    }


}