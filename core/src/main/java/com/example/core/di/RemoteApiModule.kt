package com.example.core.di

import android.text.TextUtils
import com.example.core.CONNECT_TIMEOUT
import com.example.core.READ_TIMEOUT
import com.example.core.WRITE_TIMEOUT
import com.example.core.util.*
import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class RemoteApiModule {
    private val BASE_URL = "https://your.api.url/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().serializeNulls().create())

    @Provides
    @Singleton
    fun provideCoroutineCallAdapterFactory(): CoroutineCallAdapterFactory = CoroutineCallAdapterFactory()

    @Provides
    @Singleton
    fun provideLiveDataCallAdapterFactory(): LiveDataCallAdapterFactory = LiveDataCallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideLiveDataResponseBodyConverterFactory(): LiveDataResponseBodyConverterFactory = LiveDataResponseBodyConverterFactory.create()

/*    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory,
        liveDataCallAdapterFactory: LiveDataCallAdapterFactory,
        liveDataResponseBodyConverterFactory: LiveDataResponseBodyConverterFactory

    ): Retrofit = Retrofit.Builder().run {
        baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .addCallAdapterFactory(liveDataCallAdapterFactory)
            .addConverterFactory(liveDataResponseBodyConverterFactory)
            .client(client).build()
    } */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().run {
        baseUrl(BASE_URL)
            .addConverterFactory(provideGsonConverterFactory())
            .addCallAdapterFactory(provideCoroutineCallAdapterFactory())
            .addCallAdapterFactory(provideLiveDataCallAdapterFactory())
            .addConverterFactory(provideLiveDataResponseBodyConverterFactory())
            .client(providesOkHttpClient())
            .build()
    }



    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
       /* @TimeOutInterceptor timeOutInterceptor : Interceptor,
        @HeaderInterceptor headers :Interceptor,
        loggingInterceptor: HttpLoggingInterceptor*/
        //  private fun provideOkHttpClient(
        // context: Applicatio{
        //  ): OkHttpClient {
        //   val cacheSize = 20 * 1024 * 1024L // 20 MB
        //   val cache = Cache(context.cacheDir, cacheSize)*//*
        val readTimeout = 17L
        val connectTimeout = 17L
        val writeTimeout = 17L

        return OkHttpClient.Builder().run {
             readTimeout(readTimeout, TimeUnit.SECONDS)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .addInterceptor(provideTimeoutInterceptor())
            .addInterceptor(provideHeaderInterceptor())
            .addInterceptor(provideLoggingInterceptor())
            .build()
        }


    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    @Provides
    @Singleton
    @HeaderInterceptor
    fun provideHeaderInterceptor(): Interceptor {
      return HeaderInceptor()
    }


     /*   return Interceptor{chain->
//            val pref = getSharedPreferenceInstance(context)
//            authToken = pref.getValue(AUTH_TOKEN_KEY, "")
//            clientId = pref.getValue(CLIENT_ID, "")
//            clientSecret = pref.getValue(CLIENT_SECRET, "")
//            val deviceId = pref.getValue(DEVICE_ID, "")
            var request = chain.request()
             *//*  request.newBuilder()
             .addHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzQ4MDIyNzIsInN1YiI6IjIzMTM1MSIsImh0dHBzOi8vcGFya3doZWVscy5jby5pbi8iOnsidXNlcl9pZCI6MjMxMzUxLCJuYW1lIjoiVGVzdCBTdXBlcnZpc29yIiwiZW1haWwiOiJkZWVwYWt2ZXJ0QGdtYWlsLmNvbSIsInBob25lX251bWJlciI6IjU1NTU1NTU1NTUiLCJyb2xlIjoic3VwZXJ2aXNvciIsImRldmljZV9pZCI6bnVsbH19.QtRonGB4FiPROFEx72xX-2OpWbPglXx6_Fz1IXrcgw0")
             .addHeader("client-id", USER_CLIENT_ID)
            .addHeader("client-secret", USER_CLIENT_SECRET)
           .addHeader("platform", PLATFORM_NAME)
            .addHeader("version-name", "1")
            .addHeader("version-code","1")
            .build()*//*
//                .header("app_name", "ABC")
//                .header("platform", "android")
//                .header("Authorization", authToken)
//                .header("client-secret", clientSecret)
//                .header("client-id", clientId)
//                .header("device-id", deviceId)
//                .build()
            val response = chain.proceed(request)
            AppLog.e("sasa-head", request.toString())
            response
        }*/
 //   }

    @Provides
    @Singleton
    @TimeOutInterceptor
    fun provideTimeoutInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()

            var connectTimeout = chain.connectTimeoutMillis()
            var readTimeout = chain.readTimeoutMillis()
            var writeTimeout = chain.writeTimeoutMillis()

            val connectNew = request.header(CONNECT_TIMEOUT)
            val readNew = request.header(READ_TIMEOUT)
            val writeNew = request.header(WRITE_TIMEOUT)

            if (!TextUtils.isEmpty(connectNew)) {
                connectTimeout = Integer.valueOf(connectNew!!)
            }
            if (!TextUtils.isEmpty(readNew)) {
                readTimeout = Integer.valueOf(readNew!!)
            }
            if (!TextUtils.isEmpty(writeNew)) {
                writeTimeout = Integer.valueOf(writeNew!!)
            }

            val builder = request.newBuilder()
            builder.removeHeader(CONNECT_TIMEOUT)
            builder.removeHeader(READ_TIMEOUT)
            builder.removeHeader(WRITE_TIMEOUT)
            chain
                .withConnectTimeout(connectTimeout, TimeUnit.SECONDS)
                .withReadTimeout(readTimeout, TimeUnit.SECONDS)
                .withWriteTimeout(writeTimeout, TimeUnit.SECONDS)
                .proceed(request)
        }
    }


    @Provides
    @Singleton
    fun providesNetworkService() : ApiInterface {
       /* return provideRetrofit(okHttpClient, gsonConverterFactory, coroutineCallAdapterFactory, liveDataCallAdapterFactory,
            liveDataResponseBodyConverterFactory).create(ApiInterface::class.java)*/
        return provideRetrofit().create(ApiInterface::class.java)

    }


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HeaderInterceptor

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TimeOutInterceptor


}
