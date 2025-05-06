package com.example.сontacts_app.presentation.contact

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import javax.inject.Inject
import androidx.fragment.app.viewModels
import com.example.сontacts_app.presentation.App
import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.сontacts_app.databinding.FragmentContactBinding
import com.example.сontacts_app.domane.entity.Contact
import com.example.сontacts_app.domane.entity.ContactSection
import com.example.сontacts_app.presentation.contact.adapter.ContactSectionAdapter
import kotlinx.coroutines.launch


class ContactFragment : Fragment() {
    private lateinit var binding:FragmentContactBinding
    @Inject
    lateinit var contactViewModelFactory: ContactViewModelFactory
    private val contactViewModel: ContactViewModel by viewModels { contactViewModelFactory }
    private lateinit var contactSectionAdapter: ContactSectionAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactSectionAdapter = ContactSectionAdapter{ contact ->
            makeCall(requireContext(), contact)
        }
        binding.recyclerViewAlphabetSections.adapter = contactSectionAdapter
        hasContactPermission()
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                contactViewModel.contacts.collect { contactList ->
                    val sections = groupContactsByFirstLetter(contactList)
                    contactSectionAdapter.submitList(sections)
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {

                contactViewModel.error.collect { textError ->

                }
            }
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERM_REQUEST) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                contactViewModel.loadContacts()
            } else {
            }
        }
    }

    private fun hasContactPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERM_REQUEST
            )
        } else {
            contactViewModel.loadContacts()
        }
    }

    private fun makeCall(context: Context, contact: Contact) {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val callIntent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:${contact.number}")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(callIntent)
        } else {
            Toast.makeText(context, "отсутствует разрешение", Toast.LENGTH_SHORT).show()
            requestCallPermission()
        }
    }

    private fun requestCallPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.CALL_PHONE),
            CALL_PHONE_PERMISSION_REQUEST_CODE
        )
    }

    private fun groupContactsByFirstLetter(contacts: List<Contact>): List<ContactSection> {
        return contacts
            .filter { it.desc.isNotBlank() }
            .groupBy { it.desc.first().uppercaseChar() }
            .map { (letter, list) -> ContactSection(letter, list) }
            .sortedBy { it.letter }
    }

    companion object {
        const val PERM_REQUEST = 100
        const val CALL_PHONE_PERMISSION_REQUEST_CODE = 101
    }
}