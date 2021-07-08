package com.jss.chucknorrisjokes.core.repository

import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.entity.Joke
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class JokeRepositoryTest : TestCase() {

    @Mock
    private lateinit var jokeDataSource: JokeDataSource
    @Mock
    private lateinit var favoriteJokeDataSource: FavoriteJokeDataSource
    @Mock
    private lateinit var category: Category
    @Mock
    private lateinit var joke: Joke

    private lateinit var jokeRepository: JokeRepository

    @Before
    fun setUpTest() {
        jokeRepository = JokeRepository(jokeDataSource, favoriteJokeDataSource)
    }

    @Test
    fun testGetRandom_callsJokeDataSourceGetRandomJoke() = runBlockingTest {
        jokeRepository.getRandom(category)
        verify(jokeDataSource, times(1)).getRandom(category)
    }

    @Test
    fun testAddFavoriteJoke_callsJokeFavoriteDataSourceAddFavoriteJoke() = runBlockingTest {
        jokeRepository.addFavoriteJoke(joke)
        verify(favoriteJokeDataSource, times(1)).addFavoriteJoke(joke)
    }

    @Test
    fun testRemoveFavoriteJoke_callsJokeFavoriteDataSourceRemoveFavoriteJoke() = runBlockingTest {
        jokeRepository.removeFavoriteJoke(joke)
        verify(favoriteJokeDataSource, times(1)).removeFavoriteJoke(joke)
    }

    @Test
    fun testGetFavoriteJoke__callsJokeFavoriteDataSourceGetFavoriteJoke() = runBlockingTest {
        jokeRepository.getFavoriteJoke(joke)
        verify(favoriteJokeDataSource, times(1)).getFavoriteJoke(joke)
    }
}