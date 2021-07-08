package com.jss.chucknorrisjokes.framework.di

import com.jss.chucknorrisjokes.core.repository.CategoryRepository
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import com.jss.chucknorrisjokes.framework.repository.RemoteCategoryDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class CategoryRepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideCategoryRepository(chuckNorrisIOService: ChuckNorrisIOService) = CategoryRepository(RemoteCategoryDataSource(chuckNorrisIOService))
}