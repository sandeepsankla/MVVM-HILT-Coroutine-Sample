package com.example.core.util

/**
 * Created by Sandeep Sankla
 */
data class ApiError(
    val message: String?,
    val code: Int,
    val status: Int?
) {
    /*override fun toString(): String {
        return DataManager.instance.gsonBuilder.toJson(this)
    }
*/
    fun isNull(): Boolean {
        return this.message.isNullOrBlank() && code == 0
    }

    fun isNotNull(): Boolean {
        return !isNull()
    }
}

