package com.jss.chucknorrisjokes.framework.di

import com.jss.chucknorrisjokes.core.repository.JokeRepository
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import com.jss.chucknorrisjokes.framework.db.DatabaseService
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsInstanceOf.any
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JokeRepositoryModuleTest : TestCase() {

    @Test
    fun testProvideJokeRepository_returnsJokeRepository() {
        val chuckNorrisIOService = mock(ChuckNorrisIOService::class.java)
        val databaseService = mock(DatabaseService::class.java)
        val jokeRepositoryModule = JokeRepositoryModule()

        val result =
            jokeRepositoryModule.provideJokeRepository(chuckNorrisIOService, databaseService)
        assertThat(result, `is`(any(JokeRepository::class.java)))
    }
}