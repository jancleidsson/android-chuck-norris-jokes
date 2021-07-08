package com.jss.chucknorrisjokes.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jss.chucknorrisjokes.core.entity.Joke

@Dao
interface JokeDao {
    @Insert(onConflict = REPLACE)
    suspend fun addFavoriteJoke(joke: Joke)

    @Delete
    suspend fun deleteFavoriteJoke(joke: Joke)

    @Query("SELECT * FROM jokes WHERE id = :jokeId")
    suspend fun getFavoriteJoke(jokeId: String): Joke
}