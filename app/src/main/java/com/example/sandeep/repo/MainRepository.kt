package com.example.sandeep.repo

import com.example.core.util.Resource
import com.example.sandeep.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainRemoteSource: MainRemoteSource) :
    MainRepoSource {
    override fun addNewPerson(
        name: String,
        city: String
    ): Flow<Resource<Person>> {
        return flow {
            emit(Resource.Loading(true))
            emit(mainRemoteSource.addNewUser(name, city))
        }
    }

    override fun getAllPerson(): Flow<Resource<Person>> {
        return flow {
             //todo sandeep
        }
    }


}