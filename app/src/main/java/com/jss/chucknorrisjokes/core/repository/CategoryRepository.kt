package com.jss.chucknorrisjokes.core.repository

class CategoryRepository(private val categoryDataSource: CategoryDataSource) {
    fun getAll() = categoryDataSource.getAll()
}