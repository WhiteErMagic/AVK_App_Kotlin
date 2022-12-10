package com.developmentavk.avk_app_kotlin.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.developmentavk.avk_app_kotlin.data.models.BarCodeDbModel

@Dao
interface BarCodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBarCodes(barCods: List<BarCodeDbModel>)

}