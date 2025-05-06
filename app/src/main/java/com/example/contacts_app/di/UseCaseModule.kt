package com.example.contacts_app.di

import com.example.contacts_app.domane.repository.Repository
import com.example.contacts_app.domane.usecase.GetContactListUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun getContactList(repository: Repository): GetContactListUseCase {
        return GetContactListUseCase(repository)
    }
}