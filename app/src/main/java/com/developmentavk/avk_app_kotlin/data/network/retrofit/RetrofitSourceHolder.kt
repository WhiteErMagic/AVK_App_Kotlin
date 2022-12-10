package com.developmentavk.avk_app_kotlin.data.network.retrofit

import com.developmentavk.avk_app_kotlin.app.utils.const.ConstData
import com.developmentavk.avk_app_kotlin.di.AppScope
import com.developmentavk.avk_app_kotlin.utils.common.BasicAuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AppScope
class RetrofitSourceHolder @Inject constructor() {
    var gerritAPI: RetrofitSource? = null

    init {
        val client =  OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(ConstData.autoLogin,     ConstData.autoPass))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ConstData.SITE_ADRR)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        gerritAPI = retrofit.create(RetrofitSource::class.java)
    }
}