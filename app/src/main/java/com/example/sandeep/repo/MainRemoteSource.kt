package com.example.sandeep.repo

import com.example.core.di.FormattedResponse
import com.example.core.util.Resource
import com.example.sandeep.User
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

/**
 * Created by Sandeep Sankla
 */
class MainRemoteSource @Inject constructor(val formattedResponse: FormattedResponse)   {

   /* suspend fun getWeatherDetails(): Resource<GenericApiResponse<User>> {

        val url = "http://api.weatherstack.com/current?access_key=313510887793bf027cb42a57e9d43d42&query=New York"
        Log.d("sasa", "in main source")
        val datares : Resource<GenericApiResponse<User>>  = formattedResponse.GetCall(url, hashMapOf())
        Log.d("sasa", "$datares")
        return datares
    }
   suspend fun getWeatherDetail(): Resource<Weather> {

        val url = "http://api.weatherstack.com/current?access_key=313510887793bf027cb42a57e9d43d42&query=New York"
        Log.d("sasa", "in main source")
        val datares : Resource<Weather>  = formattedResponse.GetCall(url, hashMapOf())
        Log.d("sasa", "$datares")
        return datares
    }
*/
    fun addNewUser(name: String, city: String): Resource<User> {
       val user = User(System.currentTimeMillis().toString(),  name, city)
       FirebaseFirestore.getInstance().collection("userList").document(user.id!!)
           .set(user)
       return Resource.Success(user)
    }


}