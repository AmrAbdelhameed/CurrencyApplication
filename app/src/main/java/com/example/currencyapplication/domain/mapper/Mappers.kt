package com.example.currencyapplication.domain.mapper

import com.example.currencyapplication.domain.model.local.ConvertCurrency
import com.example.currencyapplication.presentation.ui.currency_details.history.ConvertCurrencyDataItem

object Mappers {
    fun ConvertCurrency.mapToConvertCurrencyDataItem(): ConvertCurrencyDataItem {
        return ConvertCurrencyDataItem(
            fromName = this.fromName,
            fromValue = this.fromValue,
            toName = this.toName,
            toValue = this.toValue,
            createdAt = this.createdAt,
            id = this.id
        )
    }
}