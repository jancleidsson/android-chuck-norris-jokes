package com.jss.chucknorrisjokes.core.usecase

import com.jss.chucknorrisjokes.core.entity.Joke
import com.jss.chucknorrisjokes.core.repository.JokeRepository
import javax.inject.Inject

class GetFavoriteJoke @Inject constructor(private val jokeRepository: JokeRepository) {
    suspend operator fun invoke(joke: Joke) = jokeRepository.getFavoriteJoke(joke)
}