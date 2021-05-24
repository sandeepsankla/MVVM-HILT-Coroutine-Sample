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
        return provideRetrofit().create(ApiInterface::class.java)

    }
    @Provides
    @Singleton
    fun providesAppLog() : AppLog {
        return AppLog()

    }


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HeaderInterceptor

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TimeOutInterceptor


}
