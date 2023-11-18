package com.example.currencyapplication.domain.usecases

import com.example.currencyapplication.domain.repository.ConvertCurrencyRepository
import javax.inject.Inject

class HistoricalUseCase @Inject constructor(private val convertCurrencyRepository: ConvertCurrencyRepository) {
    suspend operator fun invoke() = convertCurrencyRepository.getAllCurrencyRates()
}