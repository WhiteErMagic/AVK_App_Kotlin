package com.developmentavk.avk_app_kotlin.di

import com.developmentavk.avk_app_kotlin.data.network.impl.NetWorkServiceImpl
import com.developmentavk.avk_app_kotlin.domain.NetWorkService
import dagger.Binds
import dagger.Module

@Module
interface NetWorkServiceModule {

    @AppScope
    @Binds
    fun bindNetWorkService(impl: NetWorkServiceImpl): NetWorkService
}