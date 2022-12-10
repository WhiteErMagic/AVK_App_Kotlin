package com.developmentavk.avk_app_kotlin.data.database.repository

import com.developmentavk.avk_app_kotlin.data.database.dao.SupplierDao
import com.developmentavk.avk_app_kotlin.data.models.SupplierDBModel
import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList
import com.developmentavk.avk_app_kotlin.domain.usecases.supliers.SupplierRepository
import javax.inject.Inject

class SupplierRepositoryImpl @Inject constructor(private val supplierDao: SupplierDao): SupplierRepository {

    override suspend fun insertSupplier(supplierDBModel: SupplierDBModel) {
        supplierDao.insertSupplier(supplierDBModel)
    }

    override suspend fun getListAllSuppliers(): List<SupplierObjectForList> {
        return supplierDao.getListAllSuppliers()
    }

    override suspend fun getListPlanSuppliers(): List<SupplierObjectForList> {
        return supplierDao.getListPlanSuppliers()
    }
}