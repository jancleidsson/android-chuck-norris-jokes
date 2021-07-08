package com.jss.chucknorrisjokes.core.usecase

import com.jss.chucknorrisjokes.core.repository.CategoryRepository
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCategoriesTest : TestCase() {

    @Test
    fun testInvoke_callsGetCategories() = runBlockingTest {
        val categoryRepository = Mockito.mock(CategoryRepository::class.java)
        val getCategories = GetCategories(categoryRepository)

        getCategories.invoke()
        Mockito.verify(categoryRepository, Mockito.times(1)).getAll()
    }
}