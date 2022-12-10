package com.developmentavk.avk_app_kotlin.domain.usecases.userprofile

class SetPassUseCase (private val userProfileRepository:UserProfileRepository){
    fun setPass(pass:String){
        return userProfileRepository.setPass(pass)
    }
}