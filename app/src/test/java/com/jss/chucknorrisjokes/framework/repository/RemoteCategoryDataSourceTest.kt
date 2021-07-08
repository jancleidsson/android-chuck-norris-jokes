package com.jss.chucknorrisjokes.framework.repository

import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteCategoryDataSourceTest : TestCase() {

    @Test
    fun testGetAll_returnsAllCategories_withCapitalizedNames() {
        val chuckNorrisIOService = mock(ChuckNorrisIOService::class.java)
        val remoteCategoryDataSource = RemoteCategoryDataSource(chuckNorrisIOService)
        val categoriesResponse = Observable.fromArray(arrayListOf("test 1", "test 2"))
        `when`(chuckNorrisIOService.getCategories()).thenReturn(categoriesResponse)

        val observer = TestObserver<List<Category>>()
        val result = remoteCategoryDataSource.getAll()
        result.subscribe(observer)
        observer.assertValue(arrayListOf(Category("Test 1"), Category("Test 2")))
        observer.dispose()
    }
}