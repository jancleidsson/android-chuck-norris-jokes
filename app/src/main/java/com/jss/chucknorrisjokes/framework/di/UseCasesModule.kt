package com.jss.chucknorrisjokes.framework.di

import com.jss.chucknorrisjokes.core.repository.CategoryRepository
import com.jss.chucknorrisjokes.core.repository.JokeRepository
import com.jss.chucknorrisjokes.core.usecase.*
import com.jss.chucknorrisjokes.framework.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @Provides
    @ViewModelScoped
    fun provideUseCases(categoryRepository: CategoryRepository, jokeRepository: JokeRepository) =
        UseCases(
            GetCategories(categoryRepository),
            GetRandomJoke(jokeRepository),
            AddFavoriteJoke(jokeRepository),
            RemoveFavoriteJoke(jokeRepository),
            GetFavoriteJoke(jokeRepository)
        )
}