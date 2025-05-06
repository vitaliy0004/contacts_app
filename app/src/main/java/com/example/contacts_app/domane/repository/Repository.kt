package com.example.contacts_app.domane.repository

import com.example.contacts_app.domane.entity.Contact

interface Repository {
    fun getContactList(): List<Contact>
}