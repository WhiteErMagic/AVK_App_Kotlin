package com.developmentavk.avk_app_kotlin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.developmentavk.avk_app_kotlin.app.utils.const.ConstData
import com.developmentavk.avk_app_kotlin.data.database.dao.BarCodeDao
import com.developmentavk.avk_app_kotlin.data.database.dao.GoodDao
import com.developmentavk.avk_app_kotlin.data.database.dao.SupplierDao
import com.developmentavk.avk_app_kotlin.data.models.BarCodeDbModel
import com.developmentavk.avk_app_kotlin.data.models.GoodDBModel
import com.developmentavk.avk_app_kotlin.data.models.SupplierDBModel

@Database(entities = [GoodDBModel::class, SupplierDBModel::class, BarCodeDbModel::class],
    version = ConstData.RELEASE_DB)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goodDao(): GoodDao
    abstract fun supplierDao(): SupplierDao
    abstract fun barCodeDao(): BarCodeDao

//    companion object{
//        @Volatile
//        private var INCTANCE: AppDatabase? = null
//        fun getDataBase(context: Context): AppDatabase {
//            val tempInctance = INCTANCE
//            if(tempInctance != null) {
//                return tempInctance
//            }
//            synchronized(this){
//                val inctance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java, "app_db"
//                ).build()
//                INCTANCE = inctance
//                return inctance
//            }
//        }
//    }
}