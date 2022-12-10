package com.developmentavk.avk_app_kotlin.utils.common

import okhttp3.Credentials
import okhttp3.Interceptor

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private val credentials: String = Credentials.basic(username, password)
    //private val string = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP);

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder()
                    .header("Authorization", credentials)
                    .build()
        return chain.proceed(request)
    }
}