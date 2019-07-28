package com.example.pratilipi.views.contactlist

import android.annotation.SuppressLint
import android.os.Build
import android.provider.ContactsContract

class ContactListScopeUtil {

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


    companion object {
        private var instance: ContactListScopeUtil? = null

        fun getInstance(): ContactListScopeUtil {
            if (instance == null) {
                instance = ContactListScopeUtil()
            }
            return instance as ContactListScopeUtil
        }
    }

    fun removeAll() {
        instance = null
    }
}