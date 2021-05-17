package com.example.rupeek.repo

import android.util.Log
import com.example.core.util.GenericApiResponse
import com.example.core.util.Resource
import com.example.rupeek.MainRemoteSource
import com.example.rupeek.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainRemoteSource: MainRemoteSource) :
    MainRepoSource {

    //private val mainRemoteSource: MainRepoSource
   override suspend fun getWeatherDetails(): Flow<Resource<GenericApiResponse<User>>> {
        Log.d("sasa","getCall in repo")
        return flow {
            emit(Resource.Loading(true))
            emit(mainRemoteSource.getWeatherDetails())
            emit(Resource.Loading(false))
        }.flowOn(Dispatchers.IO)
    }

}