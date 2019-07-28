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


// Defines the selection clause
private const val SELECTION: String = "${ContactsContract.Data.LOOKUP_KEY} = ?"
/*
 * Defines a string that specifies a sort order of MIME type
 */
private const val SORT_ORDER = ContactsContract.Data.MIMETYPE

// Defines a constant that identifies the loader
private const val DETAILS_QUERY_ID: Int = 0


class DetailActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var detailActivityViewModel: DetailActivityViewModel

    lateinit var binding: ActivityDetailBinding


    private val PROJECTION: Array<out String> = arrayOf(
        ContactsContract.Data._ID,
        ContactsContract.Data.MIMETYPE,
        ContactsContract.Data.DATA1,
        ContactsContract.Data.DATA2,
        ContactsContract.Data.DATA3,
        ContactsContract.Data.DATA4,
        ContactsContract.Data.DATA5,
        ContactsContract.Data.DATA6,
        ContactsContract.Data.DATA7,
        ContactsContract.Data.DATA8,
        ContactsContract.Data.DATA9,
        ContactsContract.Data.DATA10,
        ContactsContract.Data.DATA11,
        ContactsContract.Data.DATA12,
        ContactsContract.Data.DATA13,
        ContactsContract.Data.DATA14,
        ContactsContract.Data.DATA15
    )

    // Defines the array to hold the search criteria
    private val selectionArgs: Array<String> = arrayOf("")
    /*
     * Defines a variable to contain the selection value. Once you
     * have the Cursor from the Contacts table, and you've selected
     * the desired row, move the row's LOOKUP_KEY value into this
     * variable.
     */
    private var lookupKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        initBinding()
        initViewModel()
        initObserver()
        setUp()
    }

    private fun setUp() {
        //detailActivityViewModel.testFun()

        // Initializes the loader framework
        supportLoaderManager.initLoader(DETAILS_QUERY_ID, null, this)
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

    override fun onCreateLoader(loaderId: Int, args: Bundle?): Loader<Cursor> {
        /*// Choose the proper action
        mLoader = when (loaderId) {
            DETAILS_QUERY_ID -> {
                // Assigns the selection parameter
                selectionArgs[0] = lookupKey.toString()
                // Starts the query
                this?.let {
                    CursorLoader(
                        it,
                        ContactsContract.Data.CONTENT_URI,
                        PROJECTION,
                        SELECTION,
                        selectionArgs,
                        SORT_ORDER
                    )
                }
            }

        }
        return mLoader*/
        lookupKey = ContactsContract.Contacts.LOOKUP_KEY
        // Assigns the selection parameter
        selectionArgs[0] = lookupKey.toString()
        // Starts the query
        return this?.let {
            CursorLoader(
                it,
                ContactsContract.Data.CONTENT_URI,
                PROJECTION,
                SELECTION,
                selectionArgs,
                SORT_ORDER
            )
        }
    }


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        when (loader.id) {
            DETAILS_QUERY_ID -> {
                /*
                 * Process the resulting Cursor here.
                 */
            }
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        when (loader.id) {
            DETAILS_QUERY_ID -> {
                /*
             * If you have current references to the Cursor,
             * remove them here.
             */
            }
        }
    }
}