package com.example.rupeek.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.core.util.AppLog
import com.example.core.util.Status
import com.example.rupeek.MainViewModel
import com.example.rupeek.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var applog : AppLog

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getCall()
        viewModel.character.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS ->
                    applog.d("sasa", "success")
                Status.ERROR ->
                    applog.d("sasa", "error")

                Status.LOADING ->
                    applog.d("sasa","Loading")
            }
        })
    }
}