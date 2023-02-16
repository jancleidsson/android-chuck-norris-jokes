package com.jss.chucknorrisjokes.framework.di

import android.app.Application
import com.jss.chucknorrisjokes.util.ServiceLocator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DatabaseServiceModule {
    @Provides
    @ViewModelScoped
    fun provideDatabaseService(application: Application) = ServiceLocator.provideDatabaseService(application)
}