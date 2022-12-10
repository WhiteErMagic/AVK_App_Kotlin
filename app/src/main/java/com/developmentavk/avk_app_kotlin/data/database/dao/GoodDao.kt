package com.developmentavk.avk_app_kotlin.data.database.dao

import androidx.room.*
import com.developmentavk.avk_app_kotlin.app.utils.const.ConstFields
import com.developmentavk.avk_app_kotlin.app.utils.const.TableNames
import com.developmentavk.avk_app_kotlin.data.models.GoodDBModel
import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList
import com.developmentavk.avk_app_kotlin.room.GoodObjectForListTuple

@Dao
interface GoodDao {

    @Query("SELECT ${ConstFields.COL_UID}, ${ConstFields.COL_SPEC}, ${ConstFields.COL_SUPPLIER}, " +
            "${ConstFields.COL_BRAND}, ${ConstFields.COL_MODEL}, ${ConstFields.COL_MEMORY}, " +
            "${ConstFields.COL_COLOR}, ${ConstFields.COL_MODEL_CODE}, ${ConstFields.COL_PCS_FACT}, " +
            "${ConstFields.COL_PCS} - ${ConstFields.COL_PCS_FACT} AS ${ConstFields.COL_PCS}, " +
            "${ConstFields.COL_IS_CHECK}, ${ConstFields.COL_MEMCOLSPEC} FROM ${TableNames.TableGoods} " +
            "WHERE ${ConstFields.COL_SUPPLIER} = :supplier AND ${ConstFields.COL_PCS} - ${ConstFields.COL_PCS_FACT} > 0 " +
            "ORDER BY ${ConstFields.COL_PURCHASE_DATE}")
    suspend fun getListGoodsBySupplier(supplier:String): List<GoodObjectForList>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGood(goodDBModel: GoodDBModel)

    @Query("SELECT COUNT(DISTINCT ${ConstFields.COL_SUPPLIER}) FROM ${TableNames.TableGoods}")
    suspend fun getMyPlanSuppliers(): Int

    @Query("DELETE FROM ${TableNames.TableGoods} WHERE ${ConstFields.COL_SUPPLIER} = :mItem")
    suspend fun deleteGoodsReleasedSuppliers(mItem: String)

    @Update(entity = GoodDBModel::class)
    suspend fun updateDataGood(goodObjectForListTuple: GoodObjectForListTuple)

    @Query("SELECT t1.${ConstFields.COL_UID}, t1.${ConstFields.COL_SPEC}, t1.${ConstFields.COL_SUPPLIER}," +
            "t1.${ConstFields.COL_BRAND}, t1.${ConstFields.COL_MODEL}, t1.${ConstFields.COL_MEMORY}, " +
            "t1.${ConstFields.COL_COLOR}, t1.${ConstFields.COL_MODEL_CODE}, t1.${ConstFields.COL_PCS_FACT}, " +
            "t1.${ConstFields.COL_PCS}," +
            "t1.${ConstFields.COL_IS_CHECK}, t1.${ConstFields.COL_MEMCOLSPEC} FROM ${TableNames.TableGoods} AS t1" +
            " INNER JOIN ${TableNames.TableBarCodes} AS t2 " +
            "ON t1.${ConstFields.COL_UID} == t2.${ConstFields.COL_UID} "+
            " AND t1.${ConstFields.COL_SPEC} == t2.${ConstFields.COL_SPEC} "+
            " WHERE t1.${ConstFields.COL_SUPPLIER} = :codeSupplier AND t2.${ConstFields.COL_BARCODE} = :barcode")
    suspend fun getGoodByBarCodeAndSupplier(codeSupplier: String, barcode: String): GoodObjectForList

}