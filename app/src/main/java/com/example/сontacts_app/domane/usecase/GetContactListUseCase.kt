package com.example.сontacts_app.domane.usecase

import com.example.сontacts_app.domane.entity.Contact
import com.example.сontacts_app.domane.repository.Repository

class GetContactListUseCase(private val repository: Repository) {
    fun getContactList(): List<Contact> {
        return repository.getContactList()
    }
}