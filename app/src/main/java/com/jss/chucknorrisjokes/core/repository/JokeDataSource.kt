package com.jss.chucknorrisjokes.core.repository

import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.entity.Joke
import io.reactivex.rxjava3.core.Observable

interface JokeDataSource {
    fun getRandom(category: Category): Observable<Joke>
}