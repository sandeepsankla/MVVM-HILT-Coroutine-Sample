package com.example.sandeep.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.core.base.BaseActivity
import com.example.core.exension.*
import com.example.core.util.GenericApiResponse
import com.example.core.util.Resource
import com.example.core.util.Status
import com.example.sandeep.MainViewModel
import com.example.sandeep.User
import com.example.sandeep.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private val viewModel: MainViewModel by viewModels()
    override fun initViewBinding(): ViewBinding =ActivityMainBinding.inflate(layoutInflater)

    override fun setUpObserver() {
        observe(viewModel.character, ::characterListener)
        observeEvent(viewModel.showSnackBar, ::showErrorMessage)
    }
    private fun characterListener(resource: Resource<GenericApiResponse<User>>) {
        applog.d(TAG, resource.status.name)
        when (resource.status) {
            Status.SUCCESS -> {
                progressBar.hide()
            }
            Status.ERROR -> {
                progressBar.hide()
                viewModel.showSnackBarMessage(resource.message.toString())
            }
            Status.LOADING -> progressBar.show()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        viewModel.getCall()
  /*      viewModel.character.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.hide()
                }
                Status.ERROR -> {
                    progressBar.hide()
                    binding.root.showSnackbar(it.message.toString(),  Snackbar.LENGTH_LONG)
                }
                Status.LOADING -> progressBar.show()

            }
        })*/
    }
}