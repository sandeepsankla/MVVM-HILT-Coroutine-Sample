package com.example.sandeep.repo

import android.util.Log
import com.example.core.di.FormattedResponse
import com.example.core.util.Resource
import com.example.sandeep.Person
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

/**
 * Created by Sandeep Sankla
 */
class MainRemoteSource @Inject constructor(val formattedResponse: FormattedResponse)   {

    fun addNewUser(name: String, city: String): Resource<Person> {
       val user = Person(System.currentTimeMillis().toString(),  name, city)
       try{
           val sasa = Firebase.firestore
           sasa.collection("userList").document(user.id!!)
               .set(user)
       }catch (e:Exception){
           e.message?.let { Log.e("sasa", it) }
       }

       return Resource.Success(user)
    }


}