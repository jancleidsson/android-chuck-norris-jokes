package com.jss.chucknorrisjokes.core.repository

import com.jss.chucknorrisjokes.core.entity.Joke

interface FavoriteJokeDataSource {
    suspend fun addFavoriteJoke(joke: Joke)
    suspend fun removeFavoriteJoke(joke: Joke)
    suspend fun getFavoriteJoke(joke: Joke): Joke?
}