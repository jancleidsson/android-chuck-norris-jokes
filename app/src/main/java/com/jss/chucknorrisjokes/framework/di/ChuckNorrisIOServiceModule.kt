package com.jss.chucknorrisjokes.framework.di

import com.jss.chucknorrisjokes.util.ServiceLocator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ChuckNorrisIOServiceModule {
    @Provides
    @ViewModelScoped
    fun provideChuckNorrisIOService() = ServiceLocator.provideChuckNorrisService()
}