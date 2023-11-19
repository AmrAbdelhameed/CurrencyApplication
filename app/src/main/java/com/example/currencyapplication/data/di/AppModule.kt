package com.example.currencyapplication.data.di

import android.app.Application
import com.example.currencyapplication.data.repository.ConvertCurrencyRepositoryImpl
import com.example.currencyapplication.data.source.local.dao.CurrencyDao
import com.example.currencyapplication.data.source.local.helper.DbHelper
import com.example.currencyapplication.data.source.local.helper.DbHelperImpl
import com.example.currencyapplication.data.source.remote.ApiService
import com.example.currencyapplication.data.source.remote.helper.ApiHelper
import com.example.currencyapplication.data.source.remote.helper.ApiHelperImpl
import com.example.currencyapplication.domain.repository.ConvertCurrencyRepository
import com.example.currencyapplication.domain.usecases.CurrencyRatesUseCase
import com.example.currencyapplication.domain.usecases.HistoricalUseCase
import com.example.currencyapplication.domain.usecases.InsertConvertCurrencyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiHelper(apiService: ApiService): ApiHelper {
        return ApiHelperImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideDbHelper(currencyDao: CurrencyDao): DbHelper {
        return DbHelperImpl(currencyDao)
    }

    @Singleton
    @Provides
    fun provideConvertCurrencyRepository(
        application: Application,
        apiHelper: ApiHelper,
        dbHelper: DbHelper
    ): ConvertCurrencyRepository {
        return ConvertCurrencyRepositoryImpl(application, apiHelper, dbHelper)
    }

    @Singleton
    @Provides
    fun provideCurrencyRatesUseCase(convertCurrencyRepository: ConvertCurrencyRepository): CurrencyRatesUseCase {
        return CurrencyRatesUseCase(convertCurrencyRepository)
    }

    @Singleton
    @Provides
    fun provideInsertConvertCurrencyUseCase(convertCurrencyRepository: ConvertCurrencyRepository): InsertConvertCurrencyUseCase {
        return InsertConvertCurrencyUseCase(convertCurrencyRepository)
    }

    @Singleton
    @Provides
    fun provideHistoricalUseCase(convertCurrencyRepository: ConvertCurrencyRepository): HistoricalUseCase {
        return HistoricalUseCase(convertCurrencyRepository)
    }
}