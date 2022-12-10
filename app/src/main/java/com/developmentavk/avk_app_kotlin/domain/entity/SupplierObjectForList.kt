package com.developmentavk.avk_app_kotlin.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SupplierObjectForList(val code_supplier: String, val name: String, val qty: String):
    Parcelable