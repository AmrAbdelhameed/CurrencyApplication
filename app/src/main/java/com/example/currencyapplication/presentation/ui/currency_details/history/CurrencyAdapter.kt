package com.example.currencyapplication.presentation.ui.currency_details.history

import com.example.currencyapplication.R
import com.example.currencyapplication.databinding.CurrencyItemBinding
import com.example.currencyapplication.presentation.base.BaseAdapter

class CurrencyAdapter(
    currencies: List<ConvertCurrencyDataItem> = listOf()
) : BaseAdapter<CurrencyItemBinding, ConvertCurrencyDataItem>(currencies) {

    override val layoutId: Int = R.layout.currency_item

    override fun bind(binding: CurrencyItemBinding, item: ConvertCurrencyDataItem) {
        binding.apply {
            convertCurrencyDataItem = item
            executePendingBindings()
        }
    }
}