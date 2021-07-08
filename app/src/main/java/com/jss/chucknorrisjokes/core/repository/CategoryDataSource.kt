package com.jss.chucknorrisjokes.core.repository

import com.jss.chucknorrisjokes.core.entity.Category
import io.reactivex.rxjava3.core.Observable

interface CategoryDataSource {
    fun getAll(): Observable<List<Category>>
}