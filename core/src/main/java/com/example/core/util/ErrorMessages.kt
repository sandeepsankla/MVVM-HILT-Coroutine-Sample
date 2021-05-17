package com.example.core.util

import okhttp3.HttpUrl
import javax.inject.Inject

/**
 * Created by Sandeep Sankla
 */
class ErrorMessages @Inject constructor(val code : Int?) {
    @Inject lateinit var appLog: AppLog
    private val TAG = ErrorMessages::class.java.simpleName
    fun getMessage():String {
         if(code == null)  return "Some thing went Wrong. Please try again ErrorCode - $code"
            var message:String
            when (code) {
                    ApiResponseCodes.SUCCESS -> message = "Successful response"
                    ApiResponseCodes.UNAUTHORIZED_ACCESS -> message = "You are not authorized to access this resource"
                    ApiResponseCodes.ACCESS_FORBIDDEN  -> message = "You are forbidden to access this resource"
                    ApiResponseCodes.BAD_REQUEST -> message=  "Missing or incorrect parameter/body passed with api request"
                    ApiResponseCodes.NOT_FOUND -> message = "The resource/url you are looking for cannot be found"
                    ApiResponseCodes.SERVER_ERROR -> message = "Oops!!! something is wrong. We have noted this error and our wizards are at work to fix it soon."
                    ApiResponseCodes.REQUEST_PARSING_ERROR -> message = "Problem parsing request parameters/body"
                    ApiResponseCodes.METHOD_NOT_ALLOWED -> message = "Incorrect REST method call on this url"
                    ApiResponseCodes.API_CONNECTION_ERROR -> message = "Server error."

                // Try guessing error by range
                in     ApiResponseCodes.INFORMATIONAL_RESPONSE_MIN..    ApiResponseCodes.INFORMATIONAL_RESPONSE_MAX -> message = "Response contains some information why it didn't work"
                in     ApiResponseCodes.SUCCESS_MIN..    ApiResponseCodes.SUCCESS_MAX -> message = "Successful api response"
                in     ApiResponseCodes.REDIRECTION_RESPONSE_MIN..    ApiResponseCodes.REDIRECTION_RESPONSE_MAX -> message = "API redirection response"
                in     ApiResponseCodes.CLIENT_ERROR_MIN..    ApiResponseCodes.CLIENT_ERROR_MAX -> message = "An app side error has occurred"
                in     ApiResponseCodes.SERVER_ERROR_MIN..    ApiResponseCodes.SERVER_ERROR_MAX -> message = "A server side error has occurred"
                else -> message = "Some thing went Wrong. Please try again"
            }

             appLog.i(TAG, message)
            return "$message ErrorCode - $code"
    }

}