package com.mbw.squarerepos.network.service

import com.mbw.squarerepos.data.model.square.SquareReposEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiService interface defines the API endpoints used in the application.
 * It uses Retrofit annotations to define API interactions.
 */
interface ApiService {

    /**
     * Fetches a list of SquareRepos from the API.
     *
     * @param page The page number to retrieve.
     * @param limit The number of items per page.
     * @return A [Response] containing a list of [SquareReposEntity].
     */
    @GET("repos")
    suspend fun getGithubRepos(
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): Response<List<SquareReposEntity>>
}
