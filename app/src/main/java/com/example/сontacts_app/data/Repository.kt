package com.example.сontacts_app.data

import android.content.Context
import android.provider.ContactsContract
import com.example.сontacts_app.domane.entity.Contact
import com.example.сontacts_app.domane.repository.Repository
import javax.inject.Inject

class Repository @Inject constructor(
    private val context: Context
) : Repository {
    override fun getContactList(): List<Contact> {
        val result = mutableListOf<Contact>()
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        )
        context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            val nameIdx =
                cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numIdx = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val photoUriIdx =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)
            while (cursor.moveToNext()) {
                val name = cursor.getString(nameIdx)
                val number = cursor.getString(numIdx)
                val photoUri = if (photoUriIdx != -1) cursor.getString(photoUriIdx) else null
                result.add(Contact(number, name, photoUri))
            }
        }
        return result
    }
}


