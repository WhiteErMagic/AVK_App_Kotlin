package com.developmentavk.avk_app_kotlin.domain.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.developmentavk.avk_app_kotlin.app.utils.const.ConstFields
import com.developmentavk.avk_app_kotlin.utils.common.ConstStatusUpdate
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodObjectForList(
    @ColumnInfo(name = ConstFields.COL_UID) val uid: String,
    @ColumnInfo(name = ConstFields.COL_SPEC) val spec: String,
    @ColumnInfo(name = ConstFields.COL_SUPPLIER) val supplier: String,
    @ColumnInfo(name = ConstFields.COL_BRAND) val brand: String?,
    @ColumnInfo(name = ConstFields.COL_MODEL) val model: String?,
    @ColumnInfo(name = ConstFields.COL_MEMORY) val memory: String?,
    @ColumnInfo(name = ConstFields.COL_COLOR) val color: String?,
    @ColumnInfo(name = ConstFields.COL_MODEL_CODE) val modelCode: String?,
    @ColumnInfo(name = ConstFields.COL_PCS) val pcs: Int = 0,
    @ColumnInfo(name = ConstFields.COL_PCS_FACT) val pcsFact: Int = 0,
    @ColumnInfo(name = ConstFields.COL_IS_CHECK) val isCheck: String? = ConstStatusUpdate.NEW,
    @ColumnInfo(name = ConstFields.COL_MEMCOLSPEC) val memcolspec: String = "") : Parcelable

