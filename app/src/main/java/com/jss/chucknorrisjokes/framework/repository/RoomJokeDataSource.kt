package com.jss.chucknorrisjokes.framework.repository

import com.jss.chucknorrisjokes.core.entity.Joke
import com.jss.chucknorrisjokes.core.repository.FavoriteJokeDataSource
import com.jss.chucknorrisjokes.framework.db.DatabaseService
import javax.inject.Inject

class RoomJokeDataSource @Inject constructor(databaseService: DatabaseService): FavoriteJokeDataSource {
    private val jokeDao = databaseService.jokeDao()
    override suspend fun addFavoriteJoke(joke: Joke) = jokeDao.addFavoriteJoke(joke)
    override suspend fun removeFavoriteJoke(joke: Joke) = jokeDao.deleteFavoriteJoke(joke)
    override suspend fun getFavoriteJoke(joke: Joke) = jokeDao.getFavoriteJoke(joke.id)
}