package com.app.chul.clashroyalysis.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    private val auth: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTYxLCJpZGVuIjoiNDEzOTIwNzAwOTQ5MzMyMDAxIiwibWQiOnsidXNlcm5hbWUiOiLtmITstrDrp4EiLCJrZXlWZXJzaW9uIjozLCJkaXNjcmltaW5hdG9yIjoiOTE0NyJ9LCJ0cyI6MTUyOTY1NDE1MDI2MH0.OEk8Ta-6GcQbrtqd6UNW4kT0e0CHZrBpnasPG6iXiTo"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authRequest = request.newBuilder()
                .header("auth", auth)
                .build()
        return chain.proceed(authRequest)
    }

}