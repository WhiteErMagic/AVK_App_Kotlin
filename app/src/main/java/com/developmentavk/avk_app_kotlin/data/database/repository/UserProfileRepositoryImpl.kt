package com.developmentavk.avk_app_kotlin.data.database.repository

import com.developmentavk.avk_app_kotlin.app.utils.const.ConstPrefs
import com.developmentavk.avk_app_kotlin.data.Prefs
import com.developmentavk.avk_app_kotlin.domain.usecases.userprofile.UserProfileRepository
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(private val prefs: Prefs): UserProfileRepository {

    override fun getLogin():String{
        return prefs.getStringPref(ConstPrefs.pLogin)
    }

    override fun getPass():String{
        return prefs.getStringPref(ConstPrefs.pPass)
    }

    override fun getName():String{
        return prefs.getStringPref(ConstPrefs.pName)
    }

    override fun getToken():String{
        return prefs.getStringPref(ConstPrefs.pToken)
    }

    override fun setLogin(login:String){
        prefs.setStringPref(ConstPrefs.pLogin, login)
    }

    override fun setPass(pass:String){
        prefs.setStringPref(ConstPrefs.pPass, pass)
    }

    override fun setName(name:String){
        prefs.setStringPref(ConstPrefs.pName, name)
    }

    override fun setToken(token:String){
        prefs.setStringPref(ConstPrefs.pToken, token)
    }
}