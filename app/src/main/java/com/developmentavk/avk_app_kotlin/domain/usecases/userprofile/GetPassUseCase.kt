package com.developmentavk.avk_app_kotlin.domain.usecases.userprofile

class GetPassUseCase (private val userProfileRepository:UserProfileRepository){
    fun getPass():String{
        return userProfileRepository.getPass()
    }
}