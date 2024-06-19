/**
 * A generic class that holds a value with its loading status.
 * @param <T> The type of data being handled.
 * @property data The data to be held, can be null.
 * @property message The message associated with the status, can be null.
 * @property statusCode The HTTP status code associated with the status, default is -1.
 */
package com.mbw.squarerepos.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int = -1
) {
    /**
     * Represents a successful state with data.
     * @param data The data retrieved or processed.
     * @param statusCode The HTTP status code associated with the success, default is -1.
     */
    class Success<T>(data: T, statusCode: Int = -1) : Resource<T>(data, statusCode = statusCode)

    /**
     * Represents a loading state optionally with data.
     * @param data The data being loaded, can be null.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)

    /**
     * Represents an error state with a message and optionally with data.
     * @param message The error message.
     * @param statusCode The HTTP status code associated with the error, default is -1.
     * @param data The data at the time of the error, can be null.
     */
    class Error<T>(message: String?, statusCode: Int = -1, data: T? = null) : Resource<T>(data, message, statusCode)
}
