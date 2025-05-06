package com.example.сontacts_app.di

import com.example.сontacts_app.domane.usecase.GetContactListUseCase
import com.example.сontacts_app.presentation.contact.ContactViewModelFactory
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