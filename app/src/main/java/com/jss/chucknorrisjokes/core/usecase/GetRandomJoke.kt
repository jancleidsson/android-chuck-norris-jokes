package com.jss.chucknorrisjokes.core.usecase

import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.repository.JokeRepository
import javax.inject.Inject

class GetRandomJoke @Inject constructor(private val jokeRepository: JokeRepository) {
    operator fun invoke(category: Category) = jokeRepository.getRandom(category)
}