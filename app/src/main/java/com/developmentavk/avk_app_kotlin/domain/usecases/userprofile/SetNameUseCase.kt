package com.developmentavk.avk_app_kotlin.domain.usecases.userprofile

class SetNameUseCase (private val userProfileRepository:UserProfileRepository){
    fun setName(name:String){
        return userProfileRepository.setName(name)
    }
}