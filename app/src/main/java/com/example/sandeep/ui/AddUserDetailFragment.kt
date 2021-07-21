package com.example.sandeep.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.exension.hide
import com.example.core.exension.observe
import com.example.core.exension.show
import com.example.core.util.Resource
import com.example.core.util.Status
import com.example.sandeep.R
import com.example.sandeep.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_add_user_details.*

/**
 * Created by Sandeep Sankla
 */

class AddUserDetailFragment : DialogFragment() {

    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this ).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.layout_add_user_details, container)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.user, ::userListener)
        btSubmit.setOnClickListener {
            val name  = etUserName.text
            val city = etUserCity.text
            if(!name.isNullOrEmpty() && !city.isNullOrEmpty()){
                viewModel.addUser(name.toString(), city.toString())

            }
        }
    }

    private fun userListener(resource: Resource<User>) {
        //applog.d(TAG, resource.status.name)
        when (resource.status) {
            Status.SUCCESS -> {
                //progressBar.hide()
                dismissAllowingStateLoss()
            }
            Status.ERROR -> {
                //progressBar.hide()
                viewModel.showSnackBarMessage(resource.message.toString())
            }
            Status.LOADING -> {
                //progressBar.show()
            }

        }
    }
}