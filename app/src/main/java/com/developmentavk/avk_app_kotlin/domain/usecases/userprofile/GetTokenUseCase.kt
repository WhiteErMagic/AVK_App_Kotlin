package com.developmentavk.avk_app_kotlin.domain.usecases.userprofile

class GetTokenUseCase (private val userProfileRepository:UserProfileRepository){
    fun getToken():String{
        return userProfileRepository.getToken()
    }
}