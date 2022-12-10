package com.developmentavk.avk_app_kotlin.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import com.developmentavk.avk_app_kotlin.app.utils.const.ConstFields
import com.developmentavk.avk_app_kotlin.utils.common.ConstStatusUpdate

@Entity(tableName = "table_goods", primaryKeys = ["uid", "spec", "supplier"], indices = [Index("supplier", unique = false)],
    foreignKeys = [ForeignKey(entity = SupplierDBModel::class, parentColumns = ["code_supplier"], childColumns = ["supplier"], onDelete = CASCADE, onUpdate = CASCADE)]
)
data class GoodDBModel(
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
    @ColumnInfo(name = ConstFields.COL_INSTRUCTION) val instruction: String?,
    @ColumnInfo(name = ConstFields.COL_CATEGORY) val category: String?,
    @ColumnInfo(name = ConstFields.COL_MASTERBOX) val masterbox: String?,
    @ColumnInfo(name = ConstFields.COL_ASK_IS_UNSEAL) val askIsUnseal: String?,
    @ColumnInfo(name = ConstFields.COL_PICKUP_DATE) val pickupDate: String?,
    @ColumnInfo(name = ConstFields.COL_PURCHASE_DATE) val purchaseDate: String?,
    @ColumnInfo(name = ConstFields.COL_IS_UNSEAL) val isUnseal: String?,
    @ColumnInfo(name = ConstFields.COL_IS_UNSEAL_TXT) val isUnsealTxt: String?,
    @ColumnInfo(name = ConstFields.COL_CATEGORY_CLOCK) val categoryClock: String?,
    @ColumnInfo(name = ConstFields.COL_NEED_IMEI) val needImei: String?,
    @ColumnInfo(name = ConstFields.COL_REMAIN_PCS) val remainPcs: Int = 0,
    @ColumnInfo(name = ConstFields.COL_UNSEAL) val unseal: Int = 0,
    @ColumnInfo(name = ConstFields.COL_ALGORITHM) val algorithm: String = "",
    @ColumnInfo(name = ConstFields.COL_MEMCOLSPEC) val memcolspec: String = "")
