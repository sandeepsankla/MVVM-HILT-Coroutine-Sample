package com.example.sandeep.repo

import com.example.core.util.Resource
import com.example.sandeep.Person
import kotlinx.coroutines.flow.Flow

interface MainRepoSource {
    fun addNewPerson(name: String, city: String):Flow<Resource<Person>>
    fun getAllPerson():Flow<Resource<Person>>
}