package com.example.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.util.AppLog
import com.example.core.util.SingleEvent
import javax.inject.Inject

/**
 * Created by Sandeep Sankla
 */
abstract  class BaseViewModel : ViewModel(){

    @Inject lateinit var applog : AppLog

    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
   public val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate


    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
   public val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate

    fun showToastMessage(errorMessage: String) {
        showToastPrivate.value = SingleEvent(errorMessage)
    }
    fun showSnackBarMessage(errorMessage: String) {
        showSnackBarPrivate.value = SingleEvent(errorMessage)
    }
}