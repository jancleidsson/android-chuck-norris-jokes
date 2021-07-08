package com.jss.chucknorrisjokes.framework.di

import com.jss.chucknorrisjokes.core.repository.CategoryRepository
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsInstanceOf.any
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryModuleTest : TestCase() {

    @Test
    fun testProvideCategoryRepository_returnsCategoryRepositoryInstance() {
        val chuckNorrisIOService = mock(ChuckNorrisIOService::class.java)
        val categoryRepositoryModule = CategoryRepositoryModule()

        val result = categoryRepositoryModule.provideCategoryRepository(chuckNorrisIOService)
        assertThat(result, `is`(any(CategoryRepository::class.java)))
    }
}