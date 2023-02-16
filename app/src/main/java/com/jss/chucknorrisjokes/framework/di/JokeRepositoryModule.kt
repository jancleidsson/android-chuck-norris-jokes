package com.jss.chucknorrisjokes.framework.di

import com.jss.chucknorrisjokes.core.repository.JokeRepository
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import com.jss.chucknorrisjokes.framework.db.DatabaseService
import com.jss.chucknorrisjokes.framework.repository.RemoteJokeDataSource
import com.jss.chucknorrisjokes.framework.repository.RoomJokeDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class JokeRepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideJokeRepository(
        chuckNorrisIOService: ChuckNorrisIOService,
        databaseService: DatabaseService
    ) = JokeRepository(
        RemoteJokeDataSource(chuckNorrisIOService),
        RoomJokeDataSource(databaseService)
    )
}