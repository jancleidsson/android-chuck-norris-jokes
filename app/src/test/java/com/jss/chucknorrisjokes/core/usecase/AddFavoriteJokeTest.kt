package com.jss.chucknorrisjokes.core.usecase

import com.jss.chucknorrisjokes.core.entity.Joke
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
class AddFavoriteJokeTest : TestCase() {

    @Test
    fun testInvoke() = runBlockingTest {
        val jokeRepository = mock(JokeRepository::class.java)
        val joke = mock(Joke::class.java)
        val addFavoriteJoke = AddFavoriteJoke(jokeRepository)

        addFavoriteJoke.invoke(joke)
        verify(jokeRepository, times(1)).addFavoriteJoke(joke)
    }
}