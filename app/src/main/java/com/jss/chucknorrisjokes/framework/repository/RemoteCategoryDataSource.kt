package com.jss.chucknorrisjokes.framework.repository

import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.repository.CategoryDataSource
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import io.reactivex.rxjava3.core.Observable
import java.util.*

class RemoteCategoryDataSource(private val chuckNorrisIOService: ChuckNorrisIOService) :
    CategoryDataSource {
    override fun getAll(): Observable<List<Category>> =
        chuckNorrisIOService.getCategories().map {
            it.map { category -> Category(category.capitalize(Locale.getDefault())) }
        }
}