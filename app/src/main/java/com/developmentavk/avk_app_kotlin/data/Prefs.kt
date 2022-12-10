package com.developmentavk.avk_app_kotlin.data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject


class Prefs @Inject constructor(context: Context){
    private val prefs: SharedPreferences = context.getSharedPreferences("Global_AVK_settings", Context.MODE_PRIVATE)
    private val ed: SharedPreferences.Editor = prefs.edit()

    fun setStringPref(name_pref: String, val_pref: String) {
        ed.putString(name_pref, val_pref)
        ed.commit()
    }

    fun setBooleanPref(name_pref: String?, val_pref: Boolean) {
        ed.putBoolean(name_pref, val_pref)
        ed.commit()
    }

    fun setIntPref(name_pref: String, val_pref: Int) {
        ed.putInt(name_pref, val_pref)
        ed.commit()
    }

    fun getStringPref(name_pref: String): String {
        return prefs.getString(name_pref, "")!!
    }

    fun getBooleanPref(name_pref: String): Boolean {
        return prefs.getBoolean(name_pref, false)
    }

    fun getIntPref(name_pref: String): Int {
        return prefs.getInt(name_pref, 0)
    }

    fun clearPrefs() {
        ed.clear()
        ed.commit()
    }
}