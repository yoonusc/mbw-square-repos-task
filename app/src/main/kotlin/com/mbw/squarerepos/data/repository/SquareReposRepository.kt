package com.mbw.squarerepos.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mbw.squarerepos.base.repository.BaseRepository
import com.mbw.squarerepos.data.dao.SquareReposDao
import com.mbw.squarerepos.data.model.square.SquareReposEntity
import com.mbw.squarerepos.network.service.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repository class for managing example data
 *
 * @property apiService The service used to make network requests
 * @property githubRepoDao The DAO used to interact with the square_repos_db database
 */
class SquareReposRepository @Inject constructor(
    private val apiService: ApiService,
    private val squareRepoDao: SquareReposDao
) : BaseRepository() {

    /**
     * Retrieves a paginated list of example entities
     *
     * @return A flow of PagingData containing the squareRepos entities
     */
    @ExperimentalPagingApi
    fun getRepos(): Flow<PagingData<SquareReposEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 50, enablePlaceholders = true),
            remoteMediator = SquareReposRemoteMediator(apiService, squareRepoDao),
            pagingSourceFactory = { squareRepoDao.getGithubReposEntityPagingSource() }
        ).flow
    }

    suspend fun updateRepo(data:SquareReposEntity){

            squareRepoDao.update(data)

    }
}
