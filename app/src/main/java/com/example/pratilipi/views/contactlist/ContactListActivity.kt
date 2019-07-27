package com.example.pratilipi.views.contactlist

import android.os.Bundle
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
import com.example.pratilipi.views.contactlist.adapter.ContactListRecyclerAdapter
import com.example.pratilipi.views.contactlist.viewmodel.ContactListActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class ContactListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var contactListActivityViewModel: ContactListActivityViewModel

    lateinit var binding: ActivityHomeBinding

    lateinit var contactListRecyclerAdapter: ContactListRecyclerAdapter
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
        contactListRecyclerAdapter = ContactListRecyclerAdapter()
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerview.setLayoutManager(linearLayoutManager)
        binding.recyclerview.setAdapter(contactListRecyclerAdapter)
    }

    private fun setUp() {
        contactListActivityViewModel.testFun()
    }

    private fun initViewModel() {
        contactListActivityViewModel = ViewModelProviders.of(this, factory).get(ContactListActivityViewModel::class.java)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contactlist)
    }

    private fun initDagger() {
        AndroidInjection.inject(this)
    }

    private fun initObserver() {
        contactListActivityViewModel.observeForLiveData().observe(this, Observer { boolean ->
            Toast.makeText(this, "Live Data Observed", Toast.LENGTH_LONG).show()
        })
    }
}