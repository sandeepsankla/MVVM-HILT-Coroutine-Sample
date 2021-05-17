package com.example.core.di

import com.example.core.util.GenericApiResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET
    suspend fun <T> get(
        @Url endPoint: String,
        @QueryMap(encoded = true) queryParams: Map<String, @JvmSuppressWildcards Any>? = null
    ): Response<GenericApiResponse<T>>

    @POST
    suspend fun <T> post(
        @Url endPoint: String,
        @Body payload: Any? = null,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>? = null
    ): Response<GenericApiResponse<T>>

    @PUT
    suspend fun <T> put(
        @Url endPoint: String,
        @Body payload: Any? = null
    ):Response<GenericApiResponse<T>>

    @DELETE
    suspend fun <T> delete(@Url endPoint: String): Response<GenericApiResponse<T>>

    @PATCH
    suspend fun <T> patch(
        @Url endPoint: String,
        @Body payload: Any? = null
    ):Response<GenericApiResponse<T>>

    @Multipart
    @POST
    suspend fun <T> multipart(
        @Url endPoint: String,
        @Part file: MultipartBody.Part?
    ): Response<GenericApiResponse<T>>
}