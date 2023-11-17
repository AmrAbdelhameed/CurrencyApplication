package com.example.currencyapplication.domain.usecases

import com.example.currencyapplication.data.repository.ConvertCurrencyRepositoryImpl
import com.example.currencyapplication.domain.model.local.ConvertCurrency
import com.example.currencyapplication.domain.repository.ConvertCurrencyRepository
import javax.inject.Inject

class InsertConvertCurrencyUseCase @Inject constructor(private val convertCurrencyRepository: ConvertCurrencyRepository) {
    suspend operator fun invoke(
        convertCurrency: ConvertCurrency
    ) = convertCurrencyRepository.insertConvertCurrency(convertCurrency)
}