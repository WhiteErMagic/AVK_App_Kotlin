package com.developmentavk.avk_app_kotlin.room

import androidx.room.ColumnInfo
import com.developmentavk.avk_app_kotlin.app.utils.const.ConstFields

data class GoodObjectForListTuple(
    @ColumnInfo(name = ConstFields.COL_UID) val uid: String,
    @ColumnInfo(name = ConstFields.COL_SPEC) val spec: String,
    @ColumnInfo(name = ConstFields.COL_SUPPLIER) val supplier: String,
    @ColumnInfo(name = ConstFields.COL_PCS_FACT) val pcsFact: Int = 0
)