package com.jss.chucknorrisjokes.framework.di

import com.jss.chucknorrisjokes.core.repository.CategoryRepository
import com.jss.chucknorrisjokes.core.repository.JokeRepository
import com.jss.chucknorrisjokes.framework.UseCases
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsInstanceOf.any
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UseCasesModuleTest : TestCase() {

    @Test
    fun testProvideUseCases_returnsUseCases() {
        val categoryRepository = mock(CategoryRepository::class.java)
        val jokeRepository = mock(JokeRepository::class.java)
        val useCasesModule = UseCasesModule()

        val result = useCasesModule.provideUseCases(categoryRepository, jokeRepository)
        assertThat(result, `is`(any(UseCases::class.java)))
    }
}