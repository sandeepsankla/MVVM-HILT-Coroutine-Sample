package com.example.core.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.core.exension.showSnackbar
import com.example.core.util.AppLog
import com.example.core.util.SingleEvent
import com.example.core.util.dismissKeyboard
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * Created by Sandeep Sankla
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var binding: ViewBinding

    @Inject lateinit var applog : AppLog

    abstract fun initViewBinding():ViewBinding
    abstract fun setUpObserver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding()
        setContentView(binding.root)
        setUpObserver()
    }

    fun showErrorMessage(message: SingleEvent<Any>){
        if(message.getContent() is String) {
            dismissKeyboard(binding.root)
            binding.root.showSnackbar(message.getContent() as String, Snackbar.LENGTH_LONG)
        }
    }
}