package com.jss.chucknorrisjokes.presentation.fragment

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.jss.chucknorrisjokes.framework.db.DatabaseService
import com.jss.chucknorrisjokes.presentation.ChuckNorrisJokesActivity
import com.jss.chucknorrisjokes.util.FakeChuckNorrisIOService
import com.jss.chucknorrisjokes.util.ServiceLocator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@LargeTest
@HiltAndroidTest
class JokesCategoryFragmentTest : TestCase() {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<ChuckNorrisJokesActivity>()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUpTest() {
        ServiceLocator.databaseService = Room.inMemoryDatabaseBuilder(context, DatabaseService::class.java).build()
    }

    @Test
    fun jokesCategoryFragment_progressDisplayed_onAppLaunch() {
//        val scenario = launchFragmentInContainer<JokesCategoryFragment>()
        activityScenarioRule.scenario.recreate()
        Thread.sleep(10000)
    }
}