package com.jss.chucknorrisjokes.framework.repository

import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.repository.JokeDataSource
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import java.util.*

class RemoteJokeDataSource(private val chuckNorrisIOService: ChuckNorrisIOService) : JokeDataSource {
    override fun getRandom(category: Category) = chuckNorrisIOService.randomJoke(
        category.name.decapitalize(Locale.getDefault()))
}


