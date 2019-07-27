package com.example.pratilipi.views.contactlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
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

    class ViewHolder : RecyclerView.ViewHolder {
        var binding: ItemDetailBinding

        constructor(itemView: ItemDetailBinding) : super(itemView.getRoot()) {
            this.binding = itemView
        }

        fun bind() {

        }
    }

}