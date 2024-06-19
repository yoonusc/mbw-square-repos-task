// BaseRepository.kt
package com.mbw.squarerepos.base.repository

import com.mbw.squarerepos.utils.Resource
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    /**
     * Makes a safe API call and handles exceptions.
     * @param call The suspend function representing the API call.
     * @param T The type of the expected response.
     * @return A Resource object representing the success or failure of the API call.
     */
    suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Resource.Success(result, response.code())
                } ?: Resource.Error("Response body is null", response.code())
            } else {
                Resource.Error("Error: ${response.message()}", response.code())
            }
        } catch (e: IOException) {
            Resource.Error("Network Error: ${e.localizedMessage}")
        } catch (e: Exception) {
            Resource.Error("Unexpected Error: ${e.localizedMessage}")
        }
    }

    /**
     * Handles caching and returning data from the database.
     * @param query The function to query the database.
     * @param T The type of the expected data.
     * @return A Resource object containing the data from the database.
     */
    protected suspend fun <T> fetchFromDatabase(query: suspend () -> T): Resource<T> {
        return try {
            val data = query()
            Resource.Success(data)
        } catch (e: Exception) {
            Resource.Error("Database Error: ${e.localizedMessage}")
        }
    }
}
