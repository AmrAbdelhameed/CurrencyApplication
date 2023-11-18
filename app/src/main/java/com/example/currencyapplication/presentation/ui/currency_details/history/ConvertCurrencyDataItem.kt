package com.example.currencyapplication.presentation.ui.currency_details.history

import com.example.currencyapplication.presentation.base.ListAdapterItem
import java.util.Date

data class ConvertCurrencyDataItem(
    var fromName: String,
    var fromValue: Double,
    var toName: String,
    var toValue: Double,
    var createdAt: Date,
    override var id: Long = 0
) : ListAdapterItem