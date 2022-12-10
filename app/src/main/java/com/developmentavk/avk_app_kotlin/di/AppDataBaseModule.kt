package com.developmentavk.avk_app_kotlin.di

import android.content.Context
import androidx.room.Room
import com.developmentavk.avk_app_kotlin.data.database.AppDatabase
import com.developmentavk.avk_app_kotlin.data.database.dao.BarCodeDao
import com.developmentavk.avk_app_kotlin.data.database.dao.GoodDao
import com.developmentavk.avk_app_kotlin.data.database.dao.SupplierDao
import dagger.Module
import dagger.Provides

@Module
class AppDataBaseModule {

    @AppScope
    @Provides
    fun providesRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-avk-db").build()
    }

    @AppScope
    @Provides
    fun providesBarCodeDao(demoDatabase: AppDatabase): BarCodeDao {
        return demoDatabase.barCodeDao()
    }

    @AppScope
    @Provides
    fun providesGoodDao(demoDatabase: AppDatabase): GoodDao {
        return demoDatabase.goodDao()
    }

    @AppScope
    @Provides
    fun providesSupplierDao(demoDatabase: AppDatabase): SupplierDao {
        return demoDatabase.supplierDao()
    }
}