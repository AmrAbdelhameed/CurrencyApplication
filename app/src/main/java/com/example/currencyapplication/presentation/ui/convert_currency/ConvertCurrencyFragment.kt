package com.example.currencyapplication.presentation.ui.convert_currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.currencyapplication.R
import com.example.currencyapplication.databinding.FragmentConvertCurrencyBinding
import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import com.example.currencyapplication.presentation.ui.convert_currency.intent.ConvertCurrencyIntent
import com.example.currencyapplication.presentation.ui.convert_currency.viewstate.ConvertCurrencyState
import com.example.currencyapplication.presentation.utils.Constants
import com.example.currencyapplication.presentation.utils.navigateSafe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConvertCurrencyFragment : Fragment(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {

    private var _binding: FragmentConvertCurrencyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConvertCurrencyViewModel by viewModels()

    private var isAbleToInsert: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentConvertCurrencyBinding.inflate(inflater, container, false)
            _binding?.apply {
                convertCurrencyViewModel = viewModel
                lifecycleOwner = activity
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeViewModel()
    }

    private fun initUI() {
        binding.swapBtn.setOnClickListener(this)
        binding.detailsBtn.setOnClickListener(this)
        binding.fromSpinner.onItemSelectedListener = this
        binding.toSpinner.onItemSelectedListener = this

        binding.fromEditText.setOnFocusChangeListener { _, _ -> isAbleToInsert = true }
        binding.toEditText.setOnFocusChangeListener { _, _ -> isAbleToInsert = true }

        binding.fromEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(editable: Editable?) {
                val str = editable?.toString()
                if (str?.isNotEmpty() == true) {
                    viewModel.currencyConvert.fromValue = str
                    binding.toEditText.setText(viewModel.getConversionResult())
                } else {
                    binding.fromEditText.setText(fromDefaultValue)
                }
            }
        })

        binding.toEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(editable: Editable?) {
                val str = editable?.toString()
                if (str?.isNotEmpty() == true) {
                    viewModel.currencyConvert.toValue = str
                    if (isAbleToInsert && viewModel.currencyConvert.fromPosition != viewModel.currencyConvert.toPosition) {
                        lifecycleScope.launch {
                            viewModel.convertCurrencyIntent.send(ConvertCurrencyIntent.InsertCurrencyConvert)
                        }
                        isAbleToInsert = false
                    }
                }
            }
        })
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    ConvertCurrencyState.Idle -> {
                        hideLoading()
                    }

                    ConvertCurrencyState.Loading -> {
                        showLoading()
                    }

                    is ConvertCurrencyState.Success -> {
                        hideLoading()
                        if (it.data != null) {
                            viewModel.setCurrencyDataItemList(it.data)
                            binding.fromEditText.setText(fromDefaultValue)
                        }
                    }

                    is ConvertCurrencyState.Error -> {
                        hideLoading()
                        Toast.makeText(activity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.from_spinner -> {
                isAbleToInsert = true
                viewModel.from = parent.selectedItem as CurrencyDataItem
                viewModel.currencyConvert.fromPosition = position
            }

            R.id.to_spinner -> {
                isAbleToInsert = true
                viewModel.to = parent.selectedItem as CurrencyDataItem
                viewModel.currencyConvert.toPosition = position
            }
        }

        if (viewModel.from.name.isNotEmpty()) {
            binding.toEditText.setText(viewModel.getConversionResult())
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.swap_btn -> {
                binding.fromSpinner.setSelection(viewModel.currencyConvert.toPosition)
                binding.fromEditText.setText(viewModel.currencyConvert.toValue)
                binding.toSpinner.setSelection(viewModel.currencyConvert.fromPosition)
                binding.toEditText.setText(viewModel.currencyConvert.fromValue)
            }

            R.id.details_btn -> {
                val bundle = Bundle()
                bundle.putParcelableArrayList(
                    Constants.BundleArguments.POPULAR_CURRENCIES,
                    viewModel.popularList
                )

                findNavController().navigateSafe(R.id.to_currency_details, bundle)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit

    private fun showLoading() {
        viewModel.isProgressbarVisibleLiveData.value = true
    }

    private fun hideLoading() {
        viewModel.isProgressbarVisibleLiveData.value = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(
            Constants.BundleArguments.FROM_POSITION,
            viewModel.currencyConvert.fromPosition
        )
        outState.putString(
            Constants.BundleArguments.FROM_VALUE,
            viewModel.currencyConvert.fromValue
        )
        outState.putInt(Constants.BundleArguments.TO_POSITION, viewModel.currencyConvert.toPosition)
        outState.putString(Constants.BundleArguments.TO_VALUE, viewModel.currencyConvert.toValue)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            viewModel.currencyConvert.fromPosition = savedInstanceState.getInt(
                Constants.BundleArguments.FROM_POSITION, 0
            );
            viewModel.currencyConvert.fromValue = savedInstanceState.getString(
                Constants.BundleArguments.FROM_VALUE,
                fromDefaultValue
            );
            viewModel.currencyConvert.toPosition = savedInstanceState.getInt(
                Constants.BundleArguments.TO_POSITION, 0
            );
            viewModel.currencyConvert.toValue = savedInstanceState.getString(
                Constants.BundleArguments.TO_VALUE, ""
            );
        } else {
            if (viewModel.currencyConvert.toValue.isEmpty()) {
                lifecycleScope.launch {
                    viewModel.convertCurrencyIntent.send(ConvertCurrencyIntent.FetchRates)
                }
            }
        }
    }

    companion object {
        private const val fromDefaultValue = "1"
    }
}