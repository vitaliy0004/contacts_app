package com.example.сontacts_app.presentation.contact.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.сontacts_app.databinding.ItemContactSectionBinding
import com.example.сontacts_app.domane.entity.Contact
import com.example.сontacts_app.domane.entity.ContactSection

class ContactSectionAdapter(
    private val onContactClick: (Contact) -> Unit
) : ListAdapter<ContactSection, SectionViewHolder>(ContactSectionDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding =
            ItemContactSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(sectionViewHolder: SectionViewHolder, position: Int) {
        val contactSection = getItem(position)
        val sectionContactsAdapter = ContactAdapter(onContactClick)
        with(sectionViewHolder.binding) {
            sectionTitle.text = contactSection.letter.toString()
            recyclerViewContacts.adapter = sectionContactsAdapter
            sectionContactsAdapter.submitList(contactSection.contacts)
        }
    }


    class ContactSectionDiffCallback : DiffUtil.ItemCallback<ContactSection>() {
        override fun areItemsTheSame(oldItem: ContactSection, newItem: ContactSection): Boolean {
            return oldItem.letter == newItem.letter
        }

        override fun areContentsTheSame(oldItem: ContactSection, newItem: ContactSection): Boolean {
            return oldItem == newItem
        }
    }
}

class SectionViewHolder(val binding: ItemContactSectionBinding) :
    RecyclerView.ViewHolder(binding.root)