package com.jss.chucknorrisjokes.framework.di

import android.app.Application
import com.jss.chucknorrisjokes.framework.db.DatabaseService
import io.mockk.every
import io.mockk.mockkObject
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DatabaseServiceModuleTest : TestCase() {

    @Test
    fun testProvideDatabaseService_returnsDatabaseService() {
        val databaseService = mock(DatabaseService::class.java)
        val application = mock(Application::class.java)
        mockkObject(DatabaseService)
        every { DatabaseService.getInstance(application) } returns databaseService

        val result = DatabaseServiceModule().provideDatabaseService(application)
        assertThat(result, Matchers.`is`(databaseService))
    }
}