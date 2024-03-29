package com.example.pratilipi.views.detail

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.pratilipi.R
import com.example.pratilipi.databinding.ActivityDetailBinding
import com.example.pratilipi.views.detail.viewmodel.DetailActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject
import com.bumptech.glide.Glide
import com.example.pratilipi.AppConstants

class DetailActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var detailUtil: DetailUtil

    private lateinit var detailActivityViewModel: DetailActivityViewModel

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        initBinding()
        initViewModel()
        initObserver()
        setUp()
    }

    private fun setUp() {
        supportLoaderManager.initLoader(DetailCons.CONTACTS_LOADER, null, this)
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
        detailActivityViewModel.observeForLiveData().observe(this, Observer {
            Toast.makeText(this, "Live Data Observed", Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateLoader(loaderId: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            this,
            ContactsContract.Contacts.CONTENT_URI,
            detailUtil.CONTACTS_PROJECTION, null, null, null
        )
    }


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        val detailModel = detailUtil.iterateLoader(data, intent.getIntExtra(AppConstants.ContactList.CURSOR_POS, 0), contentResolver)
        binding.item = detailModel
        Glide.with(this).load(detailModel.displayUri).into(binding.image)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        // do nothing
    }

    override fun onDestroy() {
        super.onDestroy()
        detailUtil.removeAll()
        supportLoaderManager.destroyLoader(DetailCons.CONTACTS_LOADER)
    }
}