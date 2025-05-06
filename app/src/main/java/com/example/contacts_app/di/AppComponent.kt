package com.example.contacts_app.di

import com.example.contacts_app.presentation.App
import com.example.contacts_app.presentation.MainActivity
import com.example.contacts_app.presentation.contact.ContactFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FactoryInjectModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {
    fun inject(app: App)
    fun inject(activity: MainActivity)
    fun inject(fragment: ContactFragment)
}