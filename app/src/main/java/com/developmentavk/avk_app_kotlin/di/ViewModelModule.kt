package com.developmentavk.avk_app_kotlin.di

import androidx.lifecycle.ViewModel
import com.developmentavk.avk_app_kotlin.presentation.goods.GoodViewModel
import com.developmentavk.avk_app_kotlin.presentation.goods.ListGoodsViewModel
import com.developmentavk.avk_app_kotlin.presentation.login.LoginViewModel
import com.developmentavk.avk_app_kotlin.presentation.main.MainWindowViewModel
import com.developmentavk.avk_app_kotlin.presentation.suppliers.ListAllSuppliersViewModel
import com.developmentavk.avk_app_kotlin.presentation.suppliers.ListPlanSuppliersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    @Binds
    fun bindLoginViewModel(impl: LoginViewModel): ViewModel

    @IntoMap
    @ViewModelKey(MainWindowViewModel::class)
    @Binds
    fun bindMainWindowViewModel(impl: MainWindowViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ListPlanSuppliersViewModel::class)
    @Binds
    fun bindListPlanSuppliersViewModel(impl: ListPlanSuppliersViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ListAllSuppliersViewModel::class)
    @Binds
    fun bindListAllSuppliersViewModel(impl: ListAllSuppliersViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ListGoodsViewModel::class)
    @Binds
    fun bindListGoodsViewModel(impl: ListGoodsViewModel): ViewModel

    @IntoMap
    @ViewModelKey(GoodViewModel::class)
    @Binds
    fun bindGoodsViewModel(impl: GoodViewModel): ViewModel

}