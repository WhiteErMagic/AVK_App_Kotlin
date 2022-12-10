package com.developmentavk.avk_app_kotlin.domain.usecases.goods

import com.developmentavk.avk_app_kotlin.room.GoodObjectForListTuple

class UpdateDataGoodUseCase(private val goodRepository: GoodRepository) {

    suspend fun updateDataGood(goodObjectForListTuple: GoodObjectForListTuple){
        goodRepository.updateDataGood(goodObjectForListTuple)
    }

}