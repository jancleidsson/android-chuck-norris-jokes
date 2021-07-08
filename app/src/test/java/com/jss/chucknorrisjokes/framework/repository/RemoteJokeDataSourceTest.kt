package com.jss.chucknorrisjokes.framework.repository

import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteJokeDataSourceTest : TestCase() {

    @Test
    fun testGetRandomJoke_returnRandomJoke() {
        val chuckNorrisIOService = mock(ChuckNorrisIOService::class.java)
        val category = Category("Test")
        val remoteCategoryDataSource = RemoteJokeDataSource(chuckNorrisIOService)

        remoteCategoryDataSource.getRandom(category)
        verify(chuckNorrisIOService, times(1)).randomJoke("test")
    }
}