package com.example.pratilipi.views.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.pratilipi.R
import com.example.pratilipi.databinding.ActivityDetailBinding
import com.example.pratilipi.views.detail.viewmodel.DetailActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var detailActivityViewModel: DetailActivityViewModel

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        initBinding()
        initViewModel()
        initObserver()
        setUp()
    }

    private fun setUp() {
        detailActivityViewModel.testFun()
    }

    private fun initViewModel() {
        detailActivityViewModel = ViewModelProviders.of(this, factory).get(DetailActivityViewModel::class.java)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
    }

    private fun initDagger() {
        AndroidInjection.inject(this)
    }

    private fun initObserver() {
        detailActivityViewModel.observeForLiveData().observe(this, Observer { boolean ->
            Toast.makeText(this, "Live Data Observed", Toast.LENGTH_LONG).show()
        })
    }
}