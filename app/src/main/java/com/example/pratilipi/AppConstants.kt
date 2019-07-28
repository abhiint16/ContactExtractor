package com.example.pratilipi

import android.annotation.SuppressLint
import android.os.Build
import android.provider.ContactsContract

object AppConstants {
    object SharedPref {
        val SHARED_PREFERENCE_NAME = "PratilipiPrefName"
    }

    object ContactList {
        const val CURSOR_POS = "cursorPos"

        const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100
    }

}