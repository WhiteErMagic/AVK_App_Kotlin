package com.developmentavk.avk_app_kotlin.domain.usecases.barcodes

import com.developmentavk.avk_app_kotlin.domain.entity.BarCode

class AddBarCodesUseCase(private val barCodeRepository: Bar–°odesRepository) {

    suspend fun insertBarCodes(barCodes: List<BarCode>){
        barCodeRepository.insertBarCodes(barCodes)
    }
}