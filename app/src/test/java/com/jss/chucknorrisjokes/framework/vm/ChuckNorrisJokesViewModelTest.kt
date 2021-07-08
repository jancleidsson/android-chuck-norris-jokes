package com.jss.chucknorrisjokes.framework.vm

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.entity.Joke
import com.jss.chucknorrisjokes.core.usecase.*
import com.jss.chucknorrisjokes.framework.UseCases
import io.mockk.every
import io.mockk.mockkConstructor
import io.mockk.mockkObject
import io.mockk.verify
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ChuckNorrisJokesViewModelTest : TestCase() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var useCases: UseCases

    @Mock
    private lateinit var getCategories: GetCategories

    @Mock
    private lateinit var getRandomJoke: GetRandomJoke

    @Mock
    private lateinit var addFavoriteJoke: AddFavoriteJoke

    @Mock
    private lateinit var removeFavoriteJoke: RemoveFavoriteJoke

    @Mock
    private lateinit var getFavoriteJoke: GetFavoriteJoke

    @Mock
    private lateinit var category: Category

    @Mock
    private lateinit var joke: Joke

    @Mock
    private lateinit var observerLoading: Observer<in Boolean>

    @Mock
    private lateinit var observerCategories: Observer<in List<Category>>

    @Mock
    private lateinit var observerRandomJoke: Observer<in Joke>

    @Mock
    private lateinit var observerIsFavoriteJoke: Observer<in Boolean>

    @Mock
    private lateinit var observerError: Observer<in Throwable>

    private lateinit var testContext: ChuckNorrisJokesViewModel

    private val dispatcher = TestCoroutineDispatcher()


    @Before
    fun testSetup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        Dispatchers.setMain(dispatcher)

        testContext = ChuckNorrisJokesViewModel(application, useCases)

        testContext.loading.observeForever(observerLoading)
        testContext.error.observeForever(observerError)
        testContext.categoriesList.observeForever(observerCategories)
        testContext.randomJoke.observeForever(observerRandomJoke)
        testContext.isFavoriteJoke.observeForever(observerIsFavoriteJoke)

        `when`(useCases.getCategories).thenReturn(getCategories)
        `when`(useCases.getRandomJoke).thenReturn(getRandomJoke)
        `when`(useCases.getFavoriteJoke).thenReturn(getFavoriteJoke)
    }

    @After
    fun testTearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetCategories_notifyAllCategories() {
        val categories = mock(List::class.java) as List<Category>
        val categoriesResponse = Observable.fromArray(categories)
        `when`(getCategories.invoke()).thenReturn(categoriesResponse)

        testContext.getCategories()
        verify(observerLoading, times(1)).onChanged(true)
        verify(observerCategories, times(1)).onChanged(categories)
        verify(observerLoading, times(1)).onChanged(false)
        verify(observerError, times(0)).onChanged(any(Throwable::class.java))
    }

    @Test
    fun testGetCategories_notifyAnError() {
        val httpException = mock(HttpException::class.java)
        `when`(getCategories.invoke()).thenReturn(Observable.error(httpException))

        testContext.getCategories()
        verify(observerLoading, times(1)).onChanged(true)
        verify(observerError, times(1)).onChanged(httpException)
        verify(observerLoading, times(1)).onChanged(false)
    }

    @Test
    fun testGetRandomJoke_notifyARandomJoke() {
        val randomJokeResponse = Observable.fromArray(joke)
        `when`(getRandomJoke.invoke(category)).thenReturn(randomJokeResponse)

        testContext.getRandomJoke(category)
        verify(observerLoading, times(1)).onChanged(true)
        verify(observerError, times(0)).onChanged(any(Throwable::class.java))
        verify(observerRandomJoke, times(1)).onChanged(joke)
        verify(observerLoading, times(1)).onChanged(false)
    }

    @Test
    fun testGetRandomJoke_notifyAnError() {
        val httpException = mock(HttpException::class.java)
        `when`(getRandomJoke.invoke(category)).thenReturn(Observable.error(httpException))

        testContext.getRandomJoke(category)
        verify(observerLoading, times(1)).onChanged(true)
        verify(observerLoading, times(1)).onChanged(false)
        verify(observerError, times(1)).onChanged(httpException)
    }

    @Test
    fun testAddFavoriteJoke_addedFavoriteJokeWithSuccess() = runBlockingTest {
        `when`(useCases.addFavoriteJoke).thenReturn(addFavoriteJoke)

        testContext.addFavoriteJoke(joke)
        verify(addFavoriteJoke, times(1)).invoke(joke)
    }


    @Test
    fun testRemoveFavoriteJoke_removeFavoriteJokeWithSuccess() = runBlockingTest {
        `when`(useCases.removeFavoriteJoke).thenReturn(removeFavoriteJoke)

        testContext.removeFavoriteJoke(joke)
        verify(removeFavoriteJoke, times(1)).invoke(joke)
    }


    @Test
    fun testIsFavoriteJoke_returnsTrue() = runBlockingTest {
        `when`(getFavoriteJoke.invoke(joke)).thenReturn(joke)

        testContext.isFavoriteJoke(joke)
        verify(observerLoading, times(1)).onChanged(true)
        verify(observerIsFavoriteJoke, times(1)).onChanged(true)
        verify(observerLoading, times(1)).onChanged(false)
    }

    @Test
    fun testIsFavoriteJoke_returnsFalse() = runBlockingTest {
        `when`(getFavoriteJoke.invoke(joke)).thenReturn(null)

        testContext.isFavoriteJoke(joke)
        verify(observerIsFavoriteJoke, times(1)).onChanged(false)
    }

    @Test
    fun testClear() {
        val compositeDisposable = mock(CompositeDisposable::class.java)
        testContext.compositeDisposable = compositeDisposable

        testContext.clear()
        assertNull(testContext.categoriesList.value)
        assertNull(testContext.randomJoke.value)
        verify(compositeDisposable, times(1)).clear()
    }
}

