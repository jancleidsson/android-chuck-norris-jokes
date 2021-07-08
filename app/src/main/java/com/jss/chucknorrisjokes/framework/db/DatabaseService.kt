package com.jss.chucknorrisjokes.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jss.chucknorrisjokes.core.entity.Joke

@Database(entities = [Joke::class], version = 1, exportSchema = false)
abstract class DatabaseService : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "favorite_joke.db"

        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): DatabaseService =
            instance ?: synchronized(this) {
                instance ?: create(context).also { instance = it }
            }
    }

    abstract fun jokeDao(): JokeDao
}