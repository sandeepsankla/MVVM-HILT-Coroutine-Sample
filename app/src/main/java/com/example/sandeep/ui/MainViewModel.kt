package com.example.sandeep

import android.util.Log
import androidx.lifecycle.*
import com.example.core.base.BaseViewModel
import com.example.core.util.GenericApiResponse
import com.example.core.util.Resource
import com.example.sandeep.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val repository : MainRepository): BaseViewModel() {

    private var _user = MutableLiveData<Resource<User>>()
    val user: LiveData<Resource<User>> = _user


    /*fun getCall(){
        Log.d("sasas","getCall in vm")
        viewModelScope.launch {
            repository.getWeatherDetails().collect {
                _character.value = it
            }
        }
    }*/

    fun addUser(name: String, city: String) {
    viewModelScope.launch {
        repository.addNewUser(name, city).collect{
            _user.value = it
        }
    }
    }
}