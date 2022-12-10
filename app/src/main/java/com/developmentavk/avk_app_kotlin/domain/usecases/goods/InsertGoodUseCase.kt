package com.developmentavk.avk_app_kotlin.domain.usecases.goods

import com.developmentavk.avk_app_kotlin.data.models.GoodDBModel

class InsertGoodUseCase(private val goodRepository: GoodRepository) {

    suspend fun insertGood(arg: GoodDBModel){
        goodRepository.insertGood(arg)
    }

}