package com.jss.chucknorrisjokes.framework.repository

import com.jss.chucknorrisjokes.core.entity.Joke
import com.jss.chucknorrisjokes.framework.db.DatabaseService
import com.jss.chucknorrisjokes.framework.db.JokeDao
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RoomJokeDataSourceTest : TestCase() {

    @Mock
    private lateinit var databaseService: DatabaseService
    @Mock
    private lateinit var jokeDao: JokeDao
    @Mock
    private lateinit var joke: Joke

    private lateinit var testContext: RoomJokeDataSource

    @Before
    fun setUpTest() {
        `when`(databaseService.jokeDao()).thenReturn(jokeDao)
        testContext = RoomJokeDataSource(databaseService)
    }

    @Test
    fun testAddFavoriteJoke_callsJokeDaoAddFavoriteJoke() = runBlockingTest {
        testContext.addFavoriteJoke(joke)
        verify(jokeDao, times(1)).addFavoriteJoke(joke)
    }

    @Test
    fun testRemoveFavoriteJoke_callsJokeDaoDeleteFavoriteJoke() = runBlockingTest {
        testContext.removeFavoriteJoke(joke)
        verify(jokeDao, times(1)).deleteFavoriteJoke(joke)
    }

    @Test
    fun testGetFavoriteJoke_callsJokeDaoGetFavoriteJoke() = runBlockingTest {
        `when`(joke.id).thenReturn("id")

        testContext.getFavoriteJoke(joke)
        verify(jokeDao, times(1)).getFavoriteJoke(joke.id)
    }
}