package com.jss.chucknorrisjokes.util

import com.jss.chucknorrisjokes.core.entity.Joke
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import io.reactivex.rxjava3.core.Observable

class FakeChuckNorrisIOService : ChuckNorrisIOService {
    private val fakeJoke = Joke(
        "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
        "qptuaBIhToiJgISgzgH6Tg",
        "https://api.chucknorris.io/jokes/qptuaBIhToiJgISgzgH6Tg",
        "Chuck Norris farted once... and then there was the great Rift Valley"
    )

    override fun randomJoke(category: String): Observable<Joke> {
        return Observable.fromArray(fakeJoke)
    }

    override fun getCategories(): Observable<ArrayList<String>> {
        return Observable.fromArray(generateCategoryList())
    }

    private fun generateCategoryList(): ArrayList<String> {
        val categories = arrayListOf<String>()
        repeat(10) { index ->
            categories.add("Categories: $index")
        }
        return categories
    }
}