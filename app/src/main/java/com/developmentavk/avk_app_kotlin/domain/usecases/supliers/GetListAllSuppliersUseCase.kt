package com.developmentavk.avk_app_kotlin.domain.usecases.supliers

import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList

class GetListAllSuppliersUseCase(private val supplierRepository: SupplierRepository) {

    suspend fun getListAllSuppliers(): List<SupplierObjectForList>{
        return supplierRepository.getListAllSuppliers()
    }

}