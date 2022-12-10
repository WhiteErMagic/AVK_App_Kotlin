package com.developmentavk.avk_app_kotlin.domain.entity

import com.google.gson.annotations.SerializedName

data class GetResultDataStruct(
    @SerializedName("goods") val goods: Array<Map<String, String>>,
    @SerializedName("suppliers") val suppliers: Array<Map<String, String>>,
    @SerializedName("barcodes") val barcodes: Array<Map<String, String>>,
    @SerializedName("numberMessage") val numberMessage: String,
    @SerializedName("dateExchange") val dateExchange: String
    )