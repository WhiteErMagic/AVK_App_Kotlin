package com.developmentavk.avk_app_kotlin.domain.usecases.supliers

import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList

class GetListPlanSuppliersUseCase(private val supplierRepository: SupplierRepository) {

    suspend fun getListPlanSuppliers(): List<SupplierObjectForList>{
        return supplierRepository.getListPlanSuppliers()
    }

}