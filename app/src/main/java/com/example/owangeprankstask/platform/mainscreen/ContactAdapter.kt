package com.example.owangeprankstask.platform.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.owangeprankstask.R
import com.example.owangeprankstask.core.data.ContactItem
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(val listener: (ContactItem) -> Unit) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private val contacts = ArrayList<ContactItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_contact, parent, false
        )
    )

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) =
        holder.bind(contacts[position])

    fun setData(newList: List<ContactItem>) {
        contacts.clear()
        contacts.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contactItem: ContactItem) {
            itemView.apply {
                phoneTextView.text = contactItem.phone
                nameTextView.text = contactItem.name
                idTextView.text = contactItem.id

                setOnClickListener { listener.invoke(contactItem) }
            }
        }
    }
}