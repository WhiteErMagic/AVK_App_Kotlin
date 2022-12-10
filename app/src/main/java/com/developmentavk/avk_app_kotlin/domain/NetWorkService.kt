package com.developmentavk.avk_app_kotlin.domain

import com.developmentavk.avk_app_kotlin.domain.entity.GetResult
import com.developmentavk.avk_app_kotlin.domain.entity.GetResultData

import retrofit2.Response

interface NetWorkService {

    suspend fun login(user: String, password: String,token: String,release: String): Response<GetResult>

    suspend fun getAllData(user: String, password: String,token: String,release: String): Response<GetResultData>

}