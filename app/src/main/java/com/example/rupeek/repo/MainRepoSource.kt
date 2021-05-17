package com.example.rupeek.repo

import com.example.core.util.GenericApiResponse
import com.example.core.util.Resource
import com.example.rupeek.User
import kotlinx.coroutines.flow.Flow

interface MainRepoSource {
   suspend  fun getWeatherDetails() : Flow<Resource<GenericApiResponse<User>>>
}