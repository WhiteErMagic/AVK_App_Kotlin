package com.developmentavk.avk_app_kotlin.app

import android.app.Application
import com.developmentavk.avk_app_kotlin.di.DaggerAppComponent

class TheApplication: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}