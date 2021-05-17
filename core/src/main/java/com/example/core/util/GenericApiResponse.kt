package com.example.core.util

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
/*import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue*/


data class GenericApiResponse<T> (
    @SerializedName("data") val data: T?,
    @SerializedName("message") val message: String?,
    @SerializedName("status") val status: Int?
)