package com.developmentavk.avk_app_kotlin.data.database.repository

import com.developmentavk.avk_app_kotlin.data.database.dao.GoodDao
import com.developmentavk.avk_app_kotlin.data.models.GoodDBModel
import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.GoodRepository
import com.developmentavk.avk_app_kotlin.room.GoodObjectForListTuple
import javax.inject.Inject

class GoodRepositoryImpl @Inject constructor(private val goodDao: GoodDao):
    GoodRepository {

    override suspend fun insertGood(goodDBModel: GoodDBModel) {
        goodDao.insertGood(goodDBModel)
    }

    override suspend fun getListGoodsBySupplier(codeSupplier: String): List<GoodObjectForList> {
        return goodDao.getListGoodsBySupplier(codeSupplier)
    }

    override suspend fun updateDataGood(goodObjectForListTuple: GoodObjectForListTuple){
        goodDao.updateDataGood(goodObjectForListTuple)
    }

    override suspend fun getGoodByBarCodeAndSupplier(codeSupplier: String, barcode: String): GoodObjectForList{
        return goodDao.getGoodByBarCodeAndSupplier(codeSupplier, barcode)
    }
}