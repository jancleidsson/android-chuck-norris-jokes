package com.jss.chucknorrisjokes.framework.di

import com.jss.chucknorrisjokes.util.FakeChuckNorrisIOService
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [ChuckNorrisIOServiceModule::class]
)
object FakeChuckNorrisIOServiceModule {
    @Provides
    fun provideChuckNorrisIOService() = FakeChuckNorrisIOService()
}