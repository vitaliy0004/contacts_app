package com.example.сontacts_app.di

import android.app.Application
import com.example.сontacts_app.domane.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(private val application: Application) {
    @Provides
    @Singleton
    fun contentProvide(): Repository {
        return com.example.сontacts_app.data.Repository(application)
    }
}