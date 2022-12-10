package com.developmentavk.avk_app_kotlin.data.database.dao

import androidx.room.*
import com.developmentavk.avk_app_kotlin.app.utils.const.ConstFields
import com.developmentavk.avk_app_kotlin.app.utils.const.TableNames
import com.developmentavk.avk_app_kotlin.data.models.SupplierDBModel
import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList

@Dao
interface SupplierDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSupplier(supplierDBModel: SupplierDBModel)

    @Query("SELECT ${ConstFields.COL_CODE_SUPPLIER}, ${ConstFields.COL_NAME}, '' as qty FROM ${TableNames.TableSuppliers} "+
            " WHERE ${ConstFields.COL_CODE_SUPPLIER} NOT IN (SELECT ${ConstFields.COL_SUPPLIER} FROM table_goods GROUP BY ${ConstFields.COL_SUPPLIER})")
    suspend fun getListAllSuppliers():List<SupplierObjectForList>

    @Query("SELECT  ${ConstFields.COL_CODE_SUPPLIER}, ${ConstFields.COL_NAME}, '' as qty FROM ${TableNames.TableSuppliers} "+
            " WHERE ${ConstFields.COL_CODE_SUPPLIER} IN (SELECT ${ConstFields.COL_SUPPLIER} FROM table_goods GROUP BY ${ConstFields.COL_SUPPLIER})")
    suspend fun getListPlanSuppliers():List<SupplierObjectForList>
}