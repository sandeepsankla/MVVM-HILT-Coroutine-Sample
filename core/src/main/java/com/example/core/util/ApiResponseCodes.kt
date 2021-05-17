package com.example.core.util

/**
 * Created by Sandeep Sankla
 */
object ApiResponseCodes {

    const val API_CONNECTION_ERROR = 1
    const val REQUEST_PARSING_ERROR = 2
    const val RESPONSE_PARSING_ERROR = 3
    const val SUCCESS = 200
    const val NOT_FOUND = 404
    const val SERVER_ERROR = 500
    const val BAD_REQUEST = 400
    const val UNAUTHORIZED_ACCESS = 401
    const val ACCESS_FORBIDDEN = 403
    const val METHOD_NOT_ALLOWED = 405
    const val INFORMATIONAL_RESPONSE_MIN = 100
    const val INFORMATIONAL_RESPONSE_MAX = 199
    const val SUCCESS_MIN = SUCCESS
    const val SUCCESS_MAX = 299
    const val REDIRECTION_RESPONSE_MIN = 300
    const val REDIRECTION_RESPONSE_MAX = 399
    const val CLIENT_ERROR_MIN = 400
    const val CLIENT_ERROR_MAX = 499
    const val SERVER_ERROR_MIN = 500
    const val SERVER_ERROR_MAX = 599
}