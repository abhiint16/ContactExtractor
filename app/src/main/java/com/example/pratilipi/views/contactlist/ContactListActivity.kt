package com.example.pratilipi.views.contactlist

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pratilipi.R
import com.example.pratilipi.databinding.ActivityContactlistBinding
import com.example.pratilipi.views.contactlist.adapter.ContactListRecyclerAdapter
import com.example.pratilipi.views.contactlist.viewmodel.ContactListActivityViewModel
import com.example.pratilipi.views.detail.DetailActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

// The column index for the _ID column
const val CONTACT_ID_INDEX: Int = 0
// The column index for the CONTACT_KEY column
const val CONTACT_KEY_INDEX: Int = 1

class ContactListActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor>,
    AdapterView.OnItemClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var contactListActivityViewModel: ContactListActivityViewModel

    lateinit var binding: ActivityContactlistBinding

    lateinit var contactListRecyclerAdapter: ContactListRecyclerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    // Defines a variable for the search string
    private val searchString: String = ""
    // Defines the array to hold values that replace the ?
    private val selectionArgs = arrayOf<String>(searchString)


    /*
 * Defines an array that contains column names to move from
 * the Cursor to the ListView.
 */
    @SuppressLint("InlinedApi")
    val FROM_COLUMNS: Array<String> = arrayOf(
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)) {
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        } else {
            ContactsContract.Contacts.DISPLAY_NAME
        }
    )
    /*
     * Defines an array that contains resource ids for the layout views
     * that get the Cursor column contents. The id is pre-defined in
     * the Android framework, so it is prefaced with "android.R.id"
     */
    val TO_IDS: IntArray = intArrayOf(android.R.id.text1)


    @SuppressLint("InlinedApi")
    val PROJECTION: Array<out String> = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.LOOKUP_KEY,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        else
            ContactsContract.Contacts.DISPLAY_NAME
    )


    // Defines the text expression
    @SuppressLint("InlinedApi")
    val SELECTION: String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} LIKE ?"
        else
            "${ContactsContract.Contacts.DISPLAY_NAME} LIKE ?"

    // Define variables for the contact the user selects
    // The contact's _ID value
    var contactId: Long = 0
    // The contact's LOOKUP_KEY
    var contactKey: String? = null
    // A content URI for the selected contact
    var contactUri: Uri? = null
    // An adapter that binds the result Cursor to the ListView
    private var cursorAdapter: SimpleCursorAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        initBinding()
        initRecyclerView()
        initListView()
        initViewModel()
        initObserver()
        setUp()
    }

    private fun initListView() {

        // Gets a CursorAdapter
        cursorAdapter = SimpleCursorAdapter(
            this,
            R.layout.item_sample,
            null,
            FROM_COLUMNS, TO_IDS,
            0
        )

        binding.listView.adapter = cursorAdapter
        // Set the item click listener to be the current fragment.
        binding.listView.onItemClickListener = this
    }

    private fun initRecyclerView() {
        /*contactListRecyclerAdapter = ContactListRecyclerAdapter()
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerview.setLayoutManager(linearLayoutManager)
        binding.recyclerview.setAdapter(contactListRecyclerAdapter)*/
    }

    private fun setUp() {
        //contactListActivityViewModel.testFun()
        // Initializes the loader
        supportLoaderManager.initLoader(0, null, this)
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
        /*
          * Makes search string into pattern and
          * stores it in the selection array
          */
        selectionArgs[0] = "%$searchString%"
        // Starts the query
        return this.let {
            return CursorLoader(
                this,
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                selectionArgs,
                null
            )
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        // Put the result Cursor in the adapter for the ListView
        cursorAdapter?.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        // Delete the reference to the existing Cursor
        cursorAdapter?.swapCursor(null)
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(this, "click done", Toast.LENGTH_LONG).show()
        // Get the Cursor
        val cursor: Cursor? = (cursorAdapter as? CursorAdapter)?.cursor?.apply {
            // Move to the selected contact
            moveToPosition(position)
            // Get the _ID value
            contactId = getLong(CONTACT_ID_INDEX)
            // Get the selected LOOKUP KEY
            contactKey = getString(CONTACT_KEY_INDEX)
            // Create the contact's content Uri
            contactUri = ContactsContract.Contacts.getLookupUri(contactId, contactKey)

            toDetailScreen()

            /** You can use contactUri as the content URI for retrieving
             * the details for a contact.*/

        }
    }

    private fun toDetailScreen() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }
}