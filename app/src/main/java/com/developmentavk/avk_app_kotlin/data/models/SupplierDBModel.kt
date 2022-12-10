package com.developmentavk.avk_app_kotlin.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.developmentavk.avk_app_kotlin.app.utils.const.ConstFields

@Entity(tableName = "table_suppliers", primaryKeys = ["id", "code_supplier"], indices = [androidx.room.Index("code_supplier", unique = true)])
data class SupplierDBModel(
    val id: Int = 0,
    @ColumnInfo(name = ConstFields.COL_CODE_SUPPLIER) val codeSupplier: String,
    @ColumnInfo(name = ConstFields.COL_NAME) val name: String,
    @ColumnInfo(name = ConstFields.COL_ADDRESS) val address: String,
    @ColumnInfo(name = ConstFields.COL_ADDRESS2) val address2: String,
    @ColumnInfo(name = ConstFields.COL_BREAK_START) val breakStart: String,
    @ColumnInfo(name = ConstFields.COL_BREAK_END) val breakEnd: String,
    @ColumnInfo(name = ConstFields.COL_PURCHASE_DATE) val purchaseDate: String,
    @ColumnInfo(name = ConstFields.COL_MANAGER) val manager: String,
    @ColumnInfo(name = ConstFields.COL_PHONE) val phone: String,
    @ColumnInfo(name = ConstFields.COL_HKZONE) val hkzone: String,
    @ColumnInfo(name = ConstFields.COL_PICKUPER) val pickuper: String,
    @ColumnInfo(name = ConstFields.COL_ENABLE) val enable: String,
    @ColumnInfo(name = ConstFields.COL_WAREHOUSE) val warehouse: String,
    @ColumnInfo(name = ConstFields.COL_DESCRIPTION) val description: String
)

