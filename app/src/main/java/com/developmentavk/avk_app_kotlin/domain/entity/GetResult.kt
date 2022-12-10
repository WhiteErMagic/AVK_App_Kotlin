package com.developmentavk.avk_app_kotlin.domain.entity

import com.google.gson.annotations.SerializedName

data class GetResult(
    @SerializedName("result") val result: Map<String, String>,
    @SerializedName("error") val error: String
)