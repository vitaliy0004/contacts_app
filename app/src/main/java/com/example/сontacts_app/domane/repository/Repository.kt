package com.example.сontacts_app.domane.repository

import com.example.сontacts_app.domane.entity.Contact

interface Repository {
    fun getContactList(): List<Contact>
}