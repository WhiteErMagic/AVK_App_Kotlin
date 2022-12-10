package com.developmentavk.avk_app_kotlin.data.network.retrofit

import com.developmentavk.avk_app_kotlin.app.utils.const.ConstData
import com.developmentavk.avk_app_kotlin.domain.entity.GetResult
import com.developmentavk.avk_app_kotlin.domain.entity.GetResultData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface RetrofitSource {

    @POST(ConstData.ENTER)
    suspend fun getAutorisation(
        @Query("login") user: String,
        @Query("pwd") password: String,
        @Query("token") token: String,
        @Query("release") release: String
    ): Response<GetResult>

    @POST(ConstData.GETALLDATA)
    suspend fun getAllData(
        @Query("login") user: String,
        @Query("pwd") password: String,
        @Query("token") token: String,
        @Query("release") release: String
    ): Response<GetResultData>

    @POST(ConstData.GETDATABYPLAN)
    suspend fun getDataByPlan(
        @Query("login") user: String,
        @Query("pwd") password: String,
        @Query("token") token: String,
        @Query("release") release: String
    ): Call<String>

    @POST(ConstData.GETDATABYPLAN)
    suspend fun getSuppliersFromServer(
        @Query("login") user: String,
        @Query("pwd") password: String,
        @Query("token") token: String,
        @Query("release") release: String
    ): Response<String>

    @Multipart
    @POST(ConstData.SENDDATA)
    fun sendData(
        @Query("login") user: String,
        @Query("pwd") password: String,
        @Query("token") token: String,
        @Query("release") release: String,
        @PartMap partMap:Map<String, String>
    ): Response<String>

    @POST(ConstData.SENDSUCCESS)
    fun sendSuccess(
        @Query("numberMessage") numberMessage: String,
        @Query("login") user: String,
        @Query("pwd") password: String,
        @Query("token") token: String,
        @Query("release") release: String
    ): Response<String>

    @POST(ConstData.SET_SUPPLIER)
    fun tryAssignSupplier(
        @Query("login") user: String,
        @Query("pwd") password: String,
        @Query("token") token: String,
        @Query("release") release: String,
        @Body part: String
    ): Response<String>
}
