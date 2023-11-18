package com.example.currencyapplication.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyapplication.domain.model.local.ConvertCurrency
import java.util.*

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConvertCurrency(convertCurrency: ConvertCurrency)

    @Query("Select * from convert_currency")
    fun getAllCurrencyRates(): List<ConvertCurrency>


    @Query("Select * from convert_currency where createdAt BETWEEN :to AND :currentDate")
    fun getAllCurrencyRates(currentDate: Date, to: Date): List<ConvertCurrency>
}