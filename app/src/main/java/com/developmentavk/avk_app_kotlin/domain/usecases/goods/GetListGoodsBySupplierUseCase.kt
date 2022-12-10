package com.developmentavk.avk_app_kotlin.domain.usecases.goods

import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList

class GetListGoodsBySupplierUseCase(private val goodRepository: GoodRepository) {

    suspend fun getListGoodsBySupplier(codeSupplier: String): List<GoodObjectForList>{
        return goodRepository.getListGoodsBySupplier(codeSupplier)
    }

}