package com.example.contacts_app.domane.usecase

import com.example.contacts_app.domane.entity.Contact
import com.example.contacts_app.domane.repository.Repository

class GetContactListUseCase(private val repository: Repository) {
    fun getContactList(): List<Contact> {
        return repository.getContactList()
    }
}