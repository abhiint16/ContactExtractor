package com.example.pratilipi.views.contactlist

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.pratilipi.AppConstants
import com.example.pratilipi.R
import com.example.pratilipi.databinding.ActivityContactlistBinding
import com.example.pratilipi.views.contactlist.viewmodel.ContactListActivityViewModel
import com.example.pratilipi.views.detail.DetailActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class ContactListActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor>,
    AdapterView.OnItemClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var contactListScopeUtil: ContactListScopeUtil

    lateinit var contactListActivityViewModel: ContactListActivityViewModel

    lateinit var binding: ActivityContactlistBinding

    private val searchString: String = ""

    private val selectionArgs = arrayOf(searchString)

    private var cursorAdapter: SimpleCursorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        initBinding()
        initListView()
        initViewModel()
        initObserver()
        checkForPermision()
        setUp()
    }

    private fun initListView() {

        cursorAdapter = SimpleCursorAdapter(
            this, R.layout.item_listview, null,
            contactListScopeUtil.FROM_COLUMNS, contactListScopeUtil.TO_IDS, 0
        )

        binding.listView.adapter = cursorAdapter

        binding.listView.onItemClickListener = this
    }

    private fun setUp() {

    }

    private fun initViewModel() {
        contactListActivityViewModel =
            ViewModelProviders.of(this, factory).get(ContactListActivityViewModel::class.java)
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

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        selectionArgs[0] = "%$searchString%"

        return this.let {
            return CursorLoader(
                this,
                ContactsContract.Contacts.CONTENT_URI,
                contactListScopeUtil.PROJECTION,
                contactListScopeUtil.SELECTION,
                selectionArgs,
                null
            )
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        cursorAdapter?.swapCursor(data)
        supportActionBar?.title = "Total Contact = " + data?.count
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        cursorAdapter?.swapCursor(null)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        (cursorAdapter as? CursorAdapter)?.cursor?.apply {
            toDetailScreen(position)
        }
    }

    private fun toDetailScreen(pos: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(AppConstants.ContactList.CURSOR_POS, pos)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        contactListScopeUtil.removeAll()
        supportLoaderManager.destroyLoader(ContactListCons.CONTACTS_LOADER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            AppConstants.ContactList.MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    initContactLoader()
                    binding.listVisibility = true
                } else {
                    binding.listVisibility = false
                }
                return
            }
        }
    }

    fun checkForPermision() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                AppConstants.ContactList.MY_PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {
            binding.listVisibility = true
            initContactLoader()
        }
    }

    private fun initContactLoader() {
        supportLoaderManager.initLoader(ContactListCons.CONTACTS_LOADER, null, this)
    }
}