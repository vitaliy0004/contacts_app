package com.example.сontacts_app.presentation.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.сontacts_app.domane.usecase.GetContactListUseCase

class ContactViewModelFactory(
    private val getContactListUseCase: GetContactListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactViewModel(
            getContactListUseCase
        ) as T
    }
}