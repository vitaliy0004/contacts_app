package com.example.contacts_app.di

import com.example.contacts_app.domane.usecase.GetContactListUseCase
import com.example.contacts_app.presentation.contact.ContactViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FactoryInjectModule {
    @Provides
    fun contentFactory(
        getContactListUseCase: GetContactListUseCase
    ): ContactViewModelFactory {
        return ContactViewModelFactory(
            getContactListUseCase
        )
    }
}