package com.jss.chucknorrisjokes.util

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.jss.chucknorrisjokes.framework.api.ChuckNorrisIOService
import com.jss.chucknorrisjokes.framework.db.DatabaseService

object ServiceLocator {

    @Volatile
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var chuckNorrisIOService: ChuckNorrisIOService? = null

    @Volatile
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var databaseService: DatabaseService? = null

    fun provideChuckNorrisService(): ChuckNorrisIOService {
        synchronized(this) {
            return chuckNorrisIOService ?: createChuckNorrisIOService()
        }
    }

    fun provideDatabaseService(app: Application): DatabaseService {
        synchronized(this) {
            return databaseService ?: getDatabaseServiceInstance(app)
        }
    }

    private fun createChuckNorrisIOService(): ChuckNorrisIOService {
        return ChuckNorrisIOService.create()
    }

    private fun getDatabaseServiceInstance(app: Application): DatabaseService {
        return DatabaseService.getInstance(app)
    }
}

