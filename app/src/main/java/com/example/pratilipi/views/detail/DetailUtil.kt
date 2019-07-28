package com.example.pratilipi.views.detail

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import com.example.pratilipi.views.detail.model.DetailModel

class DetailUtil {

    // Columns to read from the Contacts table
    val CONTACTS_PROJECTION = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.LOOKUP_KEY,
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        ContactsContract.Contacts.PHOTO_URI
    )

    // Columns to read from the Contacts Data table
    val DATA_PROJECTION =
        arrayOf(ContactsContract.Data._ID, ContactsContract.Data.MIMETYPE, ContactsContract.Data.DATA1)

    // for Phone
    val DATA_SELECTION = ContactsContract.Data.LOOKUP_KEY + " = ?" + " AND (" +
            ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "')"

    // for Email
    val DATA_SELECTION1 = ContactsContract.Data.LOOKUP_KEY + " = ?" + " AND (" +
            ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE + "')"

    companion object {
        private var instance: DetailUtil? = null

        fun getInstance(): DetailUtil {
            if (instance == null) {
                instance = DetailUtil()
            }
            return instance as DetailUtil
        }
    }

    fun removeAll() {
        instance = null
    }

    fun iterateLoader(data: Cursor?, pos: Int, contentResolver: ContentResolver): DetailModel {
        val detailModel: DetailModel
        var id: Long = 0
        var lookupKey = ""
        var displayName = ""
        var displayUri = ""
        var phone = ""
        var email = ""

        while (data?.moveToPosition(pos)!!) {
            id = data.getLong(DetailCons.CONTACT_ID)
            lookupKey = data.getString(DetailCons.CONTACT_LOOKUP_KEY)
            displayName = data.getString(DetailCons.CONTACT_DISPLAY_NAME)
            if (data.getString(DetailCons.PHOTO_URI) != null)
                displayUri = data.getString(DetailCons.PHOTO_URI)

            val details = contentResolver.query(
                ContactsContract.Data.CONTENT_URI,
                DATA_PROJECTION,
                DATA_SELECTION,
                arrayOf(lookupKey),
                null
            )

            val details1 = contentResolver.query(
                ContactsContract.Data.CONTENT_URI,
                DATA_PROJECTION,
                DATA_SELECTION1,
                arrayOf(lookupKey),
                null
            )

            while (details!!.moveToNext()) {
                val mime = details.getString(DetailCons.CONTACT_MIMETYPE)
                if (mime == ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE) {
                    phone = details.getString(DetailCons.CONTACT_DATA1)
                }
            }

            while (details1!!.moveToNext()) {
                val mime = details1.getString(DetailCons.CONTACT_MIMETYPE)
                if (mime == ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE) {
                    email = details1.getString(DetailCons.CONTACT_DATA1)
                }
            }

            details.close()
            details1.close()

            break
        }
        detailModel = DetailModel(id, displayName, displayUri, phone, email)
        return detailModel
    }

}
