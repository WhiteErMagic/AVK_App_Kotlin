package com.developmentavk.avk_app_kotlin.domain

import androidx.fragment.app.Fragment

fun Fragment.navigator():Navigator{
    return requireActivity() as Navigator
}

interface Navigator {

    fun goToNext(fragment: Fragment)

    fun goToBack()

}