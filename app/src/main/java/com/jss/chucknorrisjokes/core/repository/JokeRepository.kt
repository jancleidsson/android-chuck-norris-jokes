package com.jss.chucknorrisjokes.core.repository

import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.entity.Joke

class JokeRepository(private val jokeDataSource: JokeDataSource, private val favoriteJokeDataSource: FavoriteJokeDataSource) {
    fun getRandom(category: Category) = jokeDataSource.getRandom(category)
    suspend fun addFavoriteJoke(joke: Joke) = favoriteJokeDataSource.addFavoriteJoke(joke)
    suspend fun removeFavoriteJoke(joke: Joke) = favoriteJokeDataSource.removeFavoriteJoke(joke)
    suspend fun getFavoriteJoke(joke: Joke) = favoriteJokeDataSource.getFavoriteJoke(joke)
}