package com.example.sandeep

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("location") val location:Location
)

data class Location(
    @SerializedName("name") val name :String?
)