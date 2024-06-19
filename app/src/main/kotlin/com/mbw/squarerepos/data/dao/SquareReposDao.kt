package com.mbw.squarerepos.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mbw.squarerepos.data.model.square.SquareReposEntity

/**
 * Data Access Object (DAO) for accessing SquareReposEntity data.
 * Provides methods for interacting with the square_repos_table in the database.
 */
@Dao
interface SquareReposDao {

    /**
     * Inserts a list of SquareRepos objects into the database.
     * If there is a conflict, the existing entries will be replaced.
     *
     * @param repos List of SquareRepos objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<SquareReposEntity>)

    /**
     * Retrieves a PagingSource of SquareRepos objects.
     *
     * @return PagingSource of SquareReposEntity objects.
     */
    @Query("SELECT * FROM square_repos_table")
    fun getGithubReposEntityPagingSource(): PagingSource<Int, SquareReposEntity>

    /**
     * Deletes all records from the github_repos.
     */
    @Query("DELETE FROM square_repos_table")
    suspend fun clearAllPosts()

    /**
     * update SquareReposEntity
     */
    @Update
    suspend fun update(data: SquareReposEntity)
}
