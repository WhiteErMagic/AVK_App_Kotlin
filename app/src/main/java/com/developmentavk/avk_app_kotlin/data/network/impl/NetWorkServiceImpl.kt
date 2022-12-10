package com.developmentavk.avk_app_kotlin.data.network.impl

import com.developmentavk.avk_app_kotlin.data.network.retrofit.RetrofitSourceHolder
import com.developmentavk.avk_app_kotlin.domain.NetWorkService
import com.developmentavk.avk_app_kotlin.domain.entity.GetResult
import com.developmentavk.avk_app_kotlin.domain.entity.GetResultData
import retrofit2.Response
import javax.inject.Inject

class NetWorkServiceImpl @Inject constructor(private val retrofitSourceHolder: RetrofitSourceHolder):
    NetWorkService {

        override suspend fun login(user: String, password: String,token: String,release: String): Response<GetResult> {
            return retrofitSourceHolder.gerritAPI!!.getAutorisation(user, password, token, release)
        }

        override suspend fun getAllData(user: String, password: String,token: String,release: String): Response<GetResultData> {
                return retrofitSourceHolder.gerritAPI!!.getAllData(user, password,token,release)
        }
}