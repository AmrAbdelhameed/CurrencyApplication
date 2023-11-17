package com.example.currencyapplication.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.currencyapplication.data.source.local.dao.CurrencyDao
import com.example.currencyapplication.domain.model.local.ConvertCurrency

@Database(entities = [ConvertCurrency::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}