package com.example.сontacts_app.presentation.contact.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.сontacts_app.R
import com.example.сontacts_app.databinding.ItemContactBinding
import com.example.сontacts_app.domane.entity.Contact


class ContactAdapter(
    private val onContactClick: (Contact) -> Unit
): ListAdapter<Contact, ContactViewHolder>(ContactDiffCallback()) {
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = getItem(position)
        with(holder.binding){
            contactDescription.text = currentContact.desc
            contactPhoneNumber.text = currentContact.number
            if (currentContact.image != null) {
                Glide.with(contactPhoto.context)
                    .load(currentContact.image)
                    .placeholder(R.drawable.icons8_contacts)
                    .into(contactPhoto)
            } else {
                contactPhoto.setImageResource(R.drawable.icons8_contacts)
            }
            contactItemView.setOnClickListener { onContactClick(currentContact) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}
class ContactViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)