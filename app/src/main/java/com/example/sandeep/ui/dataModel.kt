package com.example.sandeep

import com.google.firebase.firestore.PropertyName
import com.google.gson.annotations.SerializedName


data class Person(
    @SerializedName("id")
    @get:PropertyName("id") @set:PropertyName("id")
    var id: String? = null,

    @SerializedName("name")
    @get:PropertyName("name") @set:PropertyName("name")
    var name: String? = null,

    @SerializedName("city")
    @get:PropertyName("city") @set:PropertyName("city")
    var city: String? = null
)
