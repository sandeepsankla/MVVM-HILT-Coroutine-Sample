package com.example.sandeep.repo

import com.example.core.util.GenericApiResponse
import com.example.core.util.Resource
import com.example.sandeep.User
import kotlinx.coroutines.flow.Flow

interface MainRepoSource {
   suspend  fun getWeatherDetails() : Flow<Resource<GenericApiResponse<User>>>
}