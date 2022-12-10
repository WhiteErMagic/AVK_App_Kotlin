package com.developmentavk.avk_app_kotlin.data.mappers

import com.developmentavk.avk_app_kotlin.data.models.BarCodeDbModel
import com.developmentavk.avk_app_kotlin.domain.entity.BarCode

class BarCodeMapper {

    fun mapEntityToDbModel(barCode: BarCode) = BarCodeDbModel(
        uid = barCode.uid,
        spec = barCode.spec,
        barcode = barCode.barcode,
        isCheck = barCode.is_check,
        photo = "",
        brand = barCode.brand,
        model = barCode.model,
        memory = barCode.memory,
        color = barCode.color,
        modelCode = barCode.model_code,
        categoryClock = barCode.category_clock,
        category = barCode.category,
        masterbox = barCode.masterbox,
        unsealUid = barCode.unseal_uid
        )

    fun mapDbModelToEntity(barCodeDbModel: BarCodeDbModel) = BarCode(
        uid = barCodeDbModel.uid,
        spec = barCodeDbModel.spec,
        barcode = barCodeDbModel.barcode,
        is_check = barCodeDbModel.isCheck,
        brand = barCodeDbModel.brand,
        model = barCodeDbModel.model,
        memory = barCodeDbModel.memory,
        color = barCodeDbModel.color,
        model_code = barCodeDbModel.modelCode,
        category_clock = barCodeDbModel.categoryClock,
        category = barCodeDbModel.category,
        masterbox = barCodeDbModel.masterbox,
        unseal_uid = barCodeDbModel.unsealUid
    )

    fun mapDbModelToEntity(barCodes: List<BarCodeDbModel>) = barCodes.map{
        mapDbModelToEntity(it)
    }

    fun mapEntityToDbModel(barCodes: List<BarCode>) = barCodes.map{
        mapEntityToDbModel(it)
    }
}