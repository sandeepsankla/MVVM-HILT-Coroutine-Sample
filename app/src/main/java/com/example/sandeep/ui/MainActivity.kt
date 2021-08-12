package com.example.sandeep.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.example.core.base.BaseActivity
import com.example.core.exension.*
import com.example.core.util.Resource
import com.example.core.util.Status
import com.example.sandeep.Person
import com.example.sandeep.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private val viewModel: MainViewModel by viewModels()
    override fun initViewBinding(): ViewBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun setUpObserver() {
        observe(viewModel.user, ::userListener)
        observeEvent(viewModel.showSnackBar, ::showErrorMessage)
    }
    private fun userListener(resource: Resource<Person>) {
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
        fab.setOnClickListener {
         AddUserDetailFragment().show(supportFragmentManager, "")
        }
    }

}