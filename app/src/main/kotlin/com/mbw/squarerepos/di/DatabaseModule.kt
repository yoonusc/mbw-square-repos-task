package com.mbw.squarerepos.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mbw.squarerepos.base.constant.DB_NAME
import com.mbw.squarerepos.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Dagger module to provide database related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of [AppDatabase].
     *
     * @param context The application context.
     * @return A singleton [AppDatabase] instance.
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DB_NAME
    ).apply {
        this.setQueryCallback(RoomDatabase.QueryCallback { sqlQuery, bindArgs ->
            println("SQL Query: $sqlQuery SQL Args: $bindArgs")
        }, Executors.newSingleThreadExecutor())
    }.build()

    /**
     * Provides the instance of ExampleDao.
     *
     * @param database The instance of [AppDatabase].
     * @return The instance of SquareReposDao.
     */
    @Singleton
    @Provides
    fun provideExampleDao(database: AppDatabase) = database.squareRepositoryDao()
}