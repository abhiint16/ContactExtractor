package com.example.pratilipi.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.pratilipi.R
import com.example.pratilipi.databinding.ActivityHomeBinding
import com.example.pratilipi.views.viewmodel.HomeActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var homeActivityViewModel: HomeActivityViewModel

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        homeActivityViewModel = ViewModelProviders.of(this, factory).get(HomeActivityViewModel::class.java)

        homeActivityViewModel.testFun()

        initObserver()
    }

    private fun initObserver() {
        homeActivityViewModel.observeForLiveData().observe(this, Observer { boolean ->
            Toast.makeText(this, "Live Data Observed", Toast.LENGTH_LONG).show()
        })
    }
}