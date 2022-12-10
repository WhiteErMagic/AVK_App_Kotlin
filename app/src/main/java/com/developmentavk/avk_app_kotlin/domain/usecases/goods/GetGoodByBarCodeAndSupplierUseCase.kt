package com.developmentavk.avk_app_kotlin.domain.usecases.goods

import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList

class GetGoodByBarCodeAndSupplierUseCase(private val goodRepository: GoodRepository) {

    suspend fun getGoodByBarCodeAndSupplierUseCase(codeSupplier: String, barcode: String): GoodObjectForList{
        return goodRepository.getGoodByBarCodeAndSupplier(codeSupplier, barcode)
    }

}