package com.developmentavk.avk_app_kotlin.di

import android.content.Context
import com.developmentavk.avk_app_kotlin.presentation.goods.GoodFragment
import com.developmentavk.avk_app_kotlin.presentation.goods.ListGoodsFragment
import com.developmentavk.avk_app_kotlin.presentation.login.LoginFragment
import com.developmentavk.avk_app_kotlin.presentation.main.MainWindowFragment
import com.developmentavk.avk_app_kotlin.presentation.suppliers.ListAllSuppliersFragment
import com.developmentavk.avk_app_kotlin.presentation.suppliers.PlanSuppliersFragment
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [DataModule::class, ViewModelModule::class, RepositoryModule::class,
    AppDataBaseModule::class])
interface AppComponent {

    fun inject(fragment: LoginFragment)
    fun inject(fragment: MainWindowFragment)
    fun inject(fragment: PlanSuppliersFragment)
    fun inject(fragment: ListAllSuppliersFragment)
    fun inject(fragment: ListGoodsFragment)
    fun inject(fragment: GoodFragment)

    @Component.Factory
    interface AppComponentFactory{

        fun create(
            @BindsInstance context: Context):AppComponent
    }
}