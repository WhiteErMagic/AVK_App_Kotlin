package com.developmentavk.avk_app_kotlin.domain.usecases.userprofile

class GetNameUseCase (private val userProfileRepository:UserProfileRepository){
    fun getName():String{
        return userProfileRepository.getName()
    }
}