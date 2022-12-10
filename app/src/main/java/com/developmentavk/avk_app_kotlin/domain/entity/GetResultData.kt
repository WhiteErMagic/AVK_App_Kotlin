package com.developmentavk.avk_app_kotlin.domain.entity

import com.google.gson.annotations.SerializedName

data class GetResultData(
    @SerializedName("result") val result: GetResultDataStruct,
    @SerializedName("error") val error: String
)