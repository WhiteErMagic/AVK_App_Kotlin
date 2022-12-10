package com.developmentavk.avk_app_kotlin.domain.usecases.userprofile

class SetLoginUseCase (private val userProfileRepository:UserProfileRepository){
    fun setLogin(login:String){
        return userProfileRepository.setLogin(login)
    }
}