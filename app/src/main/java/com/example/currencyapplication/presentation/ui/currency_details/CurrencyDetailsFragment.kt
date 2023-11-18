package com.example.currencyapplication.presentation.ui.currency_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.currencyapplication.databinding.FragmentCurrencyDetailsBinding
import com.example.currencyapplication.presentation.ui.convert_currency.CurrencyDataItem
import com.example.currencyapplication.presentation.ui.currency_details.history.CurrencyAdapter
import com.example.currencyapplication.presentation.ui.currency_details.popular_currencies.CurrencyPopularAdapter
import com.example.currencyapplication.presentation.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDetailsFragment : Fragment() {

    private var _binding: FragmentCurrencyDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CurrencyDetailViewModel by viewModels()

    private val currencyAdapter by lazy { CurrencyAdapter() }

    private val currencyPopularAdapter by lazy { CurrencyPopularAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val popularCurrencies = it.getParcelableArrayList<CurrencyDataItem>(
                Constants.BundleArguments.POPULAR_CURRENCIES
            )
            if (popularCurrencies != null) {
                viewModel.setPopularOtherCurrencies(popularCurrencies)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyDetailsBinding.inflate(inflater, container, false)
        _binding?.apply {
            currencyDetailViewModel = viewModel
            lifecycleOwner = activity
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.currencyAdapter = currencyAdapter
        binding.currencyPopularAdapter = currencyPopularAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}