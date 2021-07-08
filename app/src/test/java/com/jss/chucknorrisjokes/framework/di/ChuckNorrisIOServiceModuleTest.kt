package com.jss.chucknorrisjokes.framework.di

import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import io.mockk.every
import io.mockk.mockkObject
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChuckNorrisIOServiceModuleTest : TestCase() {

    @Test
    fun testProvideChuckNorrisIOService_returnsChuckNorrisService() {
        val chuckNorrisIOService = mock(ChuckNorrisIOService::class.java)
        mockkObject(ChuckNorrisIOService.Companion)
        every { ChuckNorrisIOService.create() } returns chuckNorrisIOService

        val result = ChuckNorrisIOServiceModule().provideChuckNorrisIOService()
        assertThat(result, `is`(chuckNorrisIOService))
    }
}