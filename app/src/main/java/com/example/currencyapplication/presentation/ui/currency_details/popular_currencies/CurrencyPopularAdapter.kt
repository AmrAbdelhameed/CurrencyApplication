package com.example.currencyapplication.presentation.ui.currency_details.popular_currencies

import com.example.currencyapplication.R
import com.example.currencyapplication.databinding.CurrencyPopularItemBinding
import com.example.currencyapplication.presentation.base.BaseAdapter
import com.example.currencyapplication.presentation.ui.convert_currency.CurrencyDataItem

class CurrencyPopularAdapter(
    currencies: List<CurrencyDataItem> = listOf()
) : BaseAdapter<CurrencyPopularItemBinding, CurrencyDataItem>(currencies) {

    override val layoutId: Int = R.layout.currency_popular_item

    override fun bind(binding: CurrencyPopularItemBinding, item: CurrencyDataItem) {
        binding.apply {
            currencyDataItem = item
            executePendingBindings()
        }
    }
}