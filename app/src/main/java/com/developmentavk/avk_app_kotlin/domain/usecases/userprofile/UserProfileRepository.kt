package com.developmentavk.avk_app_kotlin.domain.usecases.userprofile

interface UserProfileRepository {

    fun getLogin():String
    fun getPass():String
    fun getName():String
    fun getToken():String

    fun setLogin(login:String)
    fun setName(name:String)
    fun setPass(pass:String)
    fun setToken(token:String)

}