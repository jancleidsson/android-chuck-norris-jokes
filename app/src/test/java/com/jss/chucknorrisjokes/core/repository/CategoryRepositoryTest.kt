package com.jss.chucknorrisjokes.core.repository

import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryTest : TestCase() {

    @Test
    fun testGetAll_callsCategoryDataSourceGetAll() = runBlockingTest {
        val categoryDataSource = mock(CategoryDataSource::class.java)
        val categoryRepository = CategoryRepository(categoryDataSource)
        categoryRepository.getAll()
        verify(categoryDataSource, times(1)).getAll()
    }
}