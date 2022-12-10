package com.developmentavk.avk_app_kotlin.di

import android.content.Context
import com.developmentavk.avk_app_kotlin.data.Prefs
import dagger.Module
import dagger.Provides


@Module
class DataModule() {

    @Provides
    @AppScope
    fun bindPrefs(context: Context): Prefs {
        return Prefs(context)
    }
}