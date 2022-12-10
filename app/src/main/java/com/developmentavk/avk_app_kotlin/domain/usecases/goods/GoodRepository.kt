package com.developmentavk.avk_app_kotlin.domain.usecases.goods

import com.developmentavk.avk_app_kotlin.data.models.GoodDBModel
import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList
import com.developmentavk.avk_app_kotlin.room.GoodObjectForListTuple

interface GoodRepository {

    suspend fun insertGood(goodDBModel: GoodDBModel)

    suspend fun getListGoodsBySupplier(codeSupplier: String): List<GoodObjectForList>

    suspend fun updateDataGood(goodObjectForListTuple: GoodObjectForListTuple)

    suspend fun getGoodByBarCodeAndSupplier(codeSupplier: String, barcode: String): GoodObjectForList
}