package com.mbw.squarerepos.network.service
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Log the request details
        println("Request URL: ${request.url}")
        println("Request Method: ${request.method}")
        println("Request Headers: ${request.headers}")
        println("Request Body: ${request.body?.toString() ?: "No Body"}")

        val response = chain.proceed(request)

        // Log the response details
        val responseBody = response.body
        val responseBodyString = responseBody?.string() ?: "No Response Body"

        println("Response Code: ${response.code}")
        println("Response Message: ${response.message}")
        println("Response Headers: ${response.headers}")
        println("Response Body: $responseBodyString")
        println("Response EBody: $responseBodyString")

        // Re-create the response before returning it because the response body can be read only once
        return response.newBuilder()
            .body(responseBodyString.toResponseBody(responseBody?.contentType()))
            .build()
    }
}

fun String.toResponseBody(contentType: okhttp3.MediaType?) = okhttp3.ResponseBody.create(contentType, this)
