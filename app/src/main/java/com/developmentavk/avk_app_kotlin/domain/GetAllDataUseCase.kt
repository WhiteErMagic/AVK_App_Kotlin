package com.developmentavk.avk_app_kotlin.domain

import com.developmentavk.avk_app_kotlin.domain.entity.GetResultData
import retrofit2.Response

class GetAllDataUseCase(private val netWorkService: NetWorkService) {
    suspend fun getAllData(user: String, password: String,token: String,release: String): Response<GetResultData>{
        return netWorkService.getAllData(user, password,token,release)
    }
}