package com.example.contacts_app.presentation.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts_app.domane.entity.Contact
import com.example.contacts_app.domane.usecase.GetContactListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ContactViewModel(
    private val getContactListUseCase: GetContactListUseCase
) : ViewModel() {
    private var _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    private var _error = MutableStateFlow<String>("")
    var error: StateFlow<String> = _error

    fun loadContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val contact = getContactListUseCase.getContactList()
                _contacts.value = contact
            } catch (e: SecurityException) {
                _error.value = "Permission denied"
            }
        }
    }
}