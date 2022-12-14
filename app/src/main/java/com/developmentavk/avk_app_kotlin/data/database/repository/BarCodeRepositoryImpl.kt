package com.developmentavk.avk_app_kotlin.data.database.repository

import com.developmentavk.avk_app_kotlin.data.database.dao.BarCodeDao
import com.developmentavk.avk_app_kotlin.data.mappers.BarCodeMapper
import com.developmentavk.avk_app_kotlin.domain.entity.BarCode
import com.developmentavk.avk_app_kotlin.domain.usecases.barcodes.Bar–°odesRepository
import javax.inject.Inject

class BarCodesRepositoryImpl @Inject constructor(private val barCodeDao: BarCodeDao):
    Bar–°odesRepository {
    private val mapper = BarCodeMapper()
    override suspend fun insertBarCodes(barCodes: List<BarCode>) {
        barCodeDao.insertBarCodes(mapper.mapEntityToDbModel(barCodes))
    }

}