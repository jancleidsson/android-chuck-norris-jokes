package com.jss.chucknorrisjokes.core.usecase

import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.repository.JokeRepository
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetRandomJokeTest : TestCase() {

    @Test
    fun testInvoke_callsGetRandomJoke() = runBlockingTest {
        val jokeRepository = mock(JokeRepository::class.java)
        val category = mock(Category::class.java)
        val getRandomJoke = GetRandomJoke(jokeRepository)

        getRandomJoke.invoke(category)
        verify(jokeRepository, times(1)).getRandom(category)
    }
}