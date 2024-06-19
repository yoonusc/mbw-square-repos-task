package com.mbw.squarerepos.data.database

import androidx.room.Database
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mbw.squarerepos.data.dao.SquareReposDao
import com.mbw.squarerepos.data.dataconvertor.TopicsConverter
import com.mbw.squarerepos.data.model.square.SquareReposEntity

/**
 * The AppDatabase class serves as the main database configuration for the Room framework.
 * It contains the database holder and serves as the main access point for the underlying connection to the app's persisted data.
 *
 * @Database annotation specifies the list of entities and version of the database.
 *
 * @property squareRepositoryDao Provides access to the SquareReposDao for database operations on SquareReposEntity.
 */
@Database(entities = [SquareReposEntity::class], version = 1)
@TypeConverters(TopicsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Returns an instance of the ExampleDao for performing database operations.
     *
     * @return SquareReposDao
     */
    abstract fun squareRepositoryDao(): SquareReposDao
    override val invalidationTracker: InvalidationTracker
        get() = super.invalidationTracker
}
