package com.mbw.squarerepos.data.repository


import android.widget.Toast
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mbw.squarerepos.data.dao.SquareReposDao
import com.mbw.squarerepos.data.model.square.SquareReposEntity
import com.mbw.squarerepos.network.service.ApiService
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

/**
 * Mediator class for handling remote data fetching using Paging 3 library.
 *
 * @param apiService The API service used for network requests.
 * @param reposDao The DAO used for interacting with the local database.
 */
@ExperimentalPagingApi
class SquareReposRemoteMediator(
    private val apiService: ApiService,
    private val reposDao: SquareReposDao
) : RemoteMediator<Int, SquareReposEntity>() {

    /**
     * Loads data incrementally based on the LoadType.
     *
     * @param loadType The type of load operation to perform.
     * @param state The current state of paging.
     *
     * @return A MediatorResult indicating success or failure.
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SquareReposEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> state.pages.size + 1
        }

        return try {
            val response = apiService.getGithubRepos(page, state.config.pageSize)
            if (response.isSuccessful) {
                response.body()?.let { squareResponse ->
                    if (loadType == LoadType.REFRESH) {
                        reposDao.clearAllPosts()
                    }
                    reposDao.insertAll(squareResponse)
                }
                MediatorResult.Success(endOfPaginationReached = response.body()?.isEmpty() ?: true)
            } else {
                var httpError=HttpException(response)
                MediatorResult.Error(httpError)
            }
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
        catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    /**
     * Called once on initialization.
     *
     * @return The action to take after initialization.
     */
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
}
