package com.developmentavk.avk_app_kotlin.domain.usecases.supliers

import com.developmentavk.avk_app_kotlin.data.models.SupplierDBModel
import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList

interface SupplierRepository {

    suspend fun insertSupplier(supplierDBModel: SupplierDBModel)

    suspend fun getListAllSuppliers(): List<SupplierObjectForList>

    suspend fun getListPlanSuppliers(): List<SupplierObjectForList>

}