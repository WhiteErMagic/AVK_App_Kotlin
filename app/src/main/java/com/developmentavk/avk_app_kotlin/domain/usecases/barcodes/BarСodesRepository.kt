package com.developmentavk.avk_app_kotlin.domain.usecases.barcodes

import com.developmentavk.avk_app_kotlin.domain.entity.BarCode

interface BarСodesRepository {

    suspend fun insertBarCodes(barCodes: List<BarCode>)
}