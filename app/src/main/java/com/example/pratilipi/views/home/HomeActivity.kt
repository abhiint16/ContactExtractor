package com.example.pratilipi.views.home

import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pratilipi.R
import com.example.pratilipi.databinding.ActivityHomeBinding
import com.example.pratilipi.views.home.adapter.DetailRecyclerAdapter
import com.example.pratilipi.views.home.viewmodel.HomeActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var homeActivityViewModel: HomeActivityViewModel

    lateinit var binding: ActivityHomeBinding

    lateinit var detailRecyclerAdapter: DetailRecyclerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        initBinding()
        initRecyclerView()
        initViewModel()
        initObserver()
        setUp()
    }

    private fun initRecyclerView() {
        detailRecyclerAdapter = DetailRecyclerAdapter()
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerview.setLayoutManager(linearLayoutManager)
        binding.recyclerview.setAdapter(detailRecyclerAdapter)
    }

    private fun setUp() {
        homeActivityViewModel.testFun()
    }

    private fun initViewModel() {
        homeActivityViewModel = ViewModelProviders.of(this, factory).get(HomeActivityViewModel::class.java)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private fun initDagger() {
        AndroidInjection.inject(this)
    }

    private fun initObserver() {
        homeActivityViewModel.observeForLiveData().observe(this, Observer { boolean ->
            Toast.makeText(this, "Live Data Observed", Toast.LENGTH_LONG).show()
        })
    }
}