package com.example.sandeep.repo

import com.example.core.util.Resource
import com.example.sandeep.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainRemoteSource: MainRemoteSource) :
    MainRepoSource {
    override fun addNewUser(
        name: String,
        city: String
    ): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(true))
            emit(mainRemoteSource.addNewUser(name, city))
        }
    }

    /*  //private val mainRemoteSource: MainRepoSource
     override suspend fun getWeatherDetails(): Flow<Resource<GenericApiResponse<User>>> {
          Log.d("sasa","getCall in repo")
          return flow {
              emit(Resource.Loading(true))
              emit(mainRemoteSource.getWeatherDetails())
              emit(Resource.Loading(false))
          }.flowOn(Dispatchers.IO)
      }*/

}