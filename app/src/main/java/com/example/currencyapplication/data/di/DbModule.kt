package com.example.currencyapplication.data.di

import android.app.Application
import androidx.room.Room
import com.example.currencyapplication.data.source.local.AppDatabase
import com.example.currencyapplication.data.source.local.dao.CurrencyDao
import com.example.currencyapplication.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, Constants.Database.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyDao(db: AppDatabase): CurrencyDao {
        return db.currencyDao()
    }
}