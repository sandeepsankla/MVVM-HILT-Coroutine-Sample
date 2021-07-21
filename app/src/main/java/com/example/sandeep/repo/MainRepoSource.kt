package com.example.sandeep.repo

import com.example.core.util.GenericApiResponse
import com.example.core.util.Resource
import com.example.sandeep.User
import kotlinx.coroutines.flow.Flow

interface MainRepoSource {
    fun addNewUser(name: String, city: String):Flow<Resource<User>>
}