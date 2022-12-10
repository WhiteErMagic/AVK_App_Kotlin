package com.developmentavk.avk_app_kotlin.domain.usecases.userprofile

class GetLoginUseCase (private val userProfileRepository:UserProfileRepository){
    fun getLogin():String{
        return userProfileRepository.getLogin()
    }
}