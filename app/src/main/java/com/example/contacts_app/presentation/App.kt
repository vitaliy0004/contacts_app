package com.example.contacts_app.presentation

import android.app.Application
import com.example.contacts_app.di.AppComponent
import com.example.contacts_app.di.DaggerAppComponent
import com.example.contacts_app.di.RepositoryModule

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .repositoryModule(RepositoryModule(this))
            .build()
        appComponent.inject(this)
    }
}