package com.developmentavk.avk_app_kotlin.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.developmentavk.avk_app_kotlin.app.utils.const.ConstFields

@Entity(tableName = "table_barcode", primaryKeys = ["barcode", "uid", "spec"])
data class BarCodeDbModel(
    @ColumnInfo(name = ConstFields.COL_UID) val uid: String,
    @ColumnInfo(name = ConstFields.COL_SPEC) val spec: String,
    @ColumnInfo(name = ConstFields.COL_BARCODE) val barcode: String,
    @ColumnInfo(name = ConstFields.COL_IS_CHECK) val isCheck: String = "0",
    @ColumnInfo(name = ConstFields.COL_PHOTO) val photo: String = "",
    @ColumnInfo(name = ConstFields.COL_BRAND) val brand: String,
    @ColumnInfo(name = ConstFields.COL_MODEL) val model: String,
    @ColumnInfo(name = ConstFields.COL_MEMORY) val memory: String,
    @ColumnInfo(name = ConstFields.COL_COLOR) val color: String,
    @ColumnInfo(name = ConstFields.COL_MODEL_CODE) val modelCode: String,
    @ColumnInfo(name = ConstFields.COL_CATEGORY_CLOCK) val categoryClock: String,
    @ColumnInfo(name = ConstFields.COL_CATEGORY) val category: String,
    @ColumnInfo(name = ConstFields.COL_MASTERBOX) val masterbox: String,
    @ColumnInfo(name = ConstFields.COL_UNSEAL_UID) val unsealUid: String)
