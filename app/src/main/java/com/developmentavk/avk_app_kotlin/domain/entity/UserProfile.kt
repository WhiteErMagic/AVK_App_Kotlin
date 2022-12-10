package com.developmentavk.avk_app_kotlin.domain.entity

import com.developmentavk.avk_app_kotlin.app.utils.const.ConstData
import com.developmentavk.avk_app_kotlin.di.AppScope
import com.developmentavk.avk_app_kotlin.domain.usecases.userprofile.*
import javax.inject.Inject

@AppScope
data class UserProfile @Inject constructor(private val userProfileRepository: UserProfileRepository){

    private val getLoginUseCase = GetLoginUseCase(userProfileRepository)
    private val getNameUseCase = GetNameUseCase(userProfileRepository)
    private val getPassUseCase = GetPassUseCase(userProfileRepository)
    private val getTokenUseCase = GetTokenUseCase(userProfileRepository)

    var name:String = getNameUseCase.getName()
    var login:String = getLoginUseCase.getLogin()
    var pass:String = getPassUseCase.getPass()
    var token:String = getTokenUseCase.getToken()
    var release = ConstData.RELEASE
}
