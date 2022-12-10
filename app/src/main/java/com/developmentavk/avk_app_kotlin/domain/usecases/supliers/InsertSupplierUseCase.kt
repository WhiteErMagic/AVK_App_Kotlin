package com.developmentavk.avk_app_kotlin.domain.usecases.supliers

import com.developmentavk.avk_app_kotlin.data.models.SupplierDBModel

class InsertSupplierUseCase(private val supplierRepository: SupplierRepository) {

    suspend fun insertSupplier(arg: SupplierDBModel){
        supplierRepository.insertSupplier(arg)
    }

}