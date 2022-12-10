package com.developmentavk.avk_app_kotlin.di

import com.developmentavk.avk_app_kotlin.data.database.repository.BarCodesRepositoryImpl
import com.developmentavk.avk_app_kotlin.data.database.repository.GoodRepositoryImpl
import com.developmentavk.avk_app_kotlin.data.database.repository.SupplierRepositoryImpl
import com.developmentavk.avk_app_kotlin.data.database.repository.UserProfileRepositoryImpl
import com.developmentavk.avk_app_kotlin.data.network.impl.NetWorkServiceImpl
import com.developmentavk.avk_app_kotlin.domain.NetWorkService
import com.developmentavk.avk_app_kotlin.domain.usecases.barcodes.BarСodesRepository
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.GoodRepository
import com.developmentavk.avk_app_kotlin.domain.usecases.supliers.SupplierRepository
import com.developmentavk.avk_app_kotlin.domain.usecases.userprofile.UserProfileRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @AppScope
    @Binds
    fun bindUserProfileRepository(impl: UserProfileRepositoryImpl): UserProfileRepository

    @AppScope
    @Binds
    fun bindGoodRepository(impl: GoodRepositoryImpl): GoodRepository

    @AppScope
    @Binds
    fun bindSupplierRepository(impl: SupplierRepositoryImpl): SupplierRepository

    @AppScope
    @Binds
    fun bindBarCodeRepository(impl: BarCodesRepositoryImpl): BarСodesRepository

    @AppScope
    @Binds
    fun bindNetWorkService(impl: NetWorkServiceImpl): NetWorkService
}