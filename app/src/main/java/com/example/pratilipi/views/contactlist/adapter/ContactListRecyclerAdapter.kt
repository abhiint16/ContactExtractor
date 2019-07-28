package com.example.pratilipi.views.contactlist.adapter

import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cursoradapter.widget.CursorAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pratilipi.AppConstants
import com.example.pratilipi.R
import com.example.pratilipi.databinding.ItemDetailBinding

class ContactListRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemDetailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_detail,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind()
    }

    override fun getItemCount(): Int {
        return 0
    }

    class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

        var binding: ItemDetailBinding

        // Define variables for the contact the user selects
        // The contact's _ID value
        var contactId: Long = 0
        // The contact's LOOKUP_KEY
        var contactKey: String? = null
        // A content URI for the selected contact
        var contactUri: Uri? = null

        constructor(itemView: ItemDetailBinding) : super(itemView.getRoot()) {
            this.binding = itemView
        }

        fun bind() {
            binding.detailsContainer.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            /*// Get the Cursor
            val cursor: Cursor? = (parent.adapter as? CursorAdapter)?.cursor?.apply {
                // Move to the selected contact
                moveToPosition(position)
                // Get the _ID value
                contactId = getLong(AppConstants.ContactList.CONTACT_ID_INDEX)
                // Get the selected LOOKUP KEY
                contactKey = getString(AppConstants.ContactList.CONTACT_KEY_INDEX)
                // Create the contact's content Uri
                contactUri = ContactsContract.Contacts.getLookupUri(contactId, mContactKey)
                *//*
                 * You can use contactUri as the content URI for retrieving
                 * the details for a contact.
                 */
            }
        }

    }
