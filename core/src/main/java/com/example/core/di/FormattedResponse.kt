package com.example.core.di

import com.example.core.util.ErrorMessages
import com.example.core.util.AppLog
import com.example.core.util.GenericApiResponse
import com.example.core.util.Resource
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Sandeep Sankla
 */

class FormattedResponse @Inject constructor(private val apiInterface: ApiInterface) {
   @Inject lateinit var appLog: AppLog

    public suspend fun <T> GetCall(apiUrl: String, queryMap: Map<String, Any>?, ):Resource<T>{
        return getResult(apiInterface.get(apiUrl, queryMap))
    }
    public suspend fun <T> PostCall(apiUrl: String, payload: Any? = null, queryMap: Map<String, Any>?):Resource<T>{
        return getResult(apiInterface.post(apiUrl,payload,  queryMap))
    } public suspend fun <T> PutCall(apiUrl: String, payload: Any? = null):Resource<T>{
        return getResult(apiInterface.put(apiUrl, payload))
    } public suspend fun <T> PatchCall(apiUrl: String, payload: Any? = null ):Resource<T>{
        return getResult(apiInterface.patch(apiUrl, payload))
    }

   private  fun <T> getResult(result: Response<GenericApiResponse<T>>?, e: Exception? = null): Resource<T> {
        if (result != null && result.isSuccessful) {
            result.body()?.let {
                if (it.status ?: 1 == 0) {
                    it.data?.let { data ->
                        return Resource.Success(data)
                    } ?: run {
                        return Resource.Error( result.message(), result.code())
                    }
                } else {
                    appLog.e("API Error: Server error -> ${it.message ?: "status is 1"} :: errorCode: 404")
                    return Resource.Error( it.message ?: ErrorMessages(404).getMessage(), result.code())
                }
            } ?: run {

                appLog.e("API Error: result.body is null :: Code: 404")
                return Resource.Error( ErrorMessages(-1).getMessage(), -1)
            }
        } else {
            e?.let {
                appLog.e("API Error: Internal error -> ${it.message ?: "Message is null"} :: Code: -1")
                return Resource.Error( it.message  ?: ErrorMessages(-1).getMessage(), -1)
            } ?: kotlin.run {
                val errorResponse = result?.errorBody()?.string()
                val errorCode =result?.raw()?.code
                appLog.e("API Error: Api failed -> ${errorResponse
                    ?: "Message is null"} :: Code: ${errorCode ?: "Code is null"}")
                return Resource.Error( errorResponse ?: ErrorMessages(errorCode).getMessage(), errorCode)
            }
        }
    }
}