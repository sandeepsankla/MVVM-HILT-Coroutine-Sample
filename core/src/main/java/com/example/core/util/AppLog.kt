package com.example.core.util

import com.example.core.BuildConfig
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Sandeep Sankla
 */

class AppLog @Inject constructor(){
    //companion object {

        private val TAG = "Logs-"

        fun init() {
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
        }


        fun i(tag:String?,infoText:String?){
            Timber.tag(TAG.plus(tag)).i(infoText)
        }

        fun i(infoText: String?){
            Timber.tag(TAG).i(infoText)
        }

        fun d(tag:String?,debugText:String?){
            Timber.tag(TAG.plus(tag)).d(debugText)
        }

        fun d(debugText: String?){
            Timber.tag(TAG).d(debugText)
        }

        fun e(tag:String?,errorText:String?){
            Timber.tag(TAG.plus(tag)).e(errorText)
        }

        fun e(errorText: String?){
            Timber.tag(TAG).e(errorText)
        }

   // }
}