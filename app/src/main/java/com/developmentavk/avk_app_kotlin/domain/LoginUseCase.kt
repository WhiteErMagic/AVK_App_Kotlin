package com.developmentavk.avk_app_kotlin.domain

import com.developmentavk.avk_app_kotlin.domain.entity.GetResult
import retrofit2.Response

class LoginUseCase(private val netWorkService: NetWorkService) {
    suspend fun login(user: String, password: String, token: String, release: String): Response<GetResult>{
        return netWorkService.login(user, password,token,release)
    }
}