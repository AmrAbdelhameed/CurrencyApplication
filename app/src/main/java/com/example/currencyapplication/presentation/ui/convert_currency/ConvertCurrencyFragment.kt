package com.example.currencyapplication.presentation.ui.convert_currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.currencyapplication.R
import com.example.currencyapplication.databinding.FragmentConvertCurrencyBinding
import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import com.example.currencyapplication.presentation.ui.convert_currency.intent.ConvertCurrencyIntent
import com.example.currencyapplication.presentation.ui.convert_currency.viewstate.ConvertCurrencyState
import com.example.currencyapplication.presentation.utils.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConvertCurrencyFragment : Fragment(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {

    private var _binding: FragmentConvertCurrencyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConvertCurrencyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConvertCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("fromPosition", viewModel.fromPosition)
        outState.putString("fromValue", viewModel.fromValue)
        outState.putInt("toPosition", viewModel.toPosition)
        outState.putString("toValue", viewModel.toValue)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            viewModel.fromPosition = savedInstanceState.getInt("fromPosition", 0);
            viewModel.fromValue = savedInstanceState.getString("fromValue", "1");
            viewModel.toPosition = savedInstanceState.getInt("toPosition", 0);
            viewModel.toValue = savedInstanceState.getString("toValue", "");
        } else {
            lifecycleScope.launch { viewModel.convertCurrencyIntent.send(ConvertCurrencyIntent.FetchRates) }
        }
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

        binding.fromEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(editable: Editable?) {
                val str = editable?.toString()
                if (str?.isNotEmpty() == true) {
                    viewModel.fromValue = str
                    binding.toEditText.setText(viewModel.getConversionResult())
                } else {
                    binding.fromEditText.setText("1")
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
                    viewModel.toValue = str
                    if (viewModel.fromPosition != viewModel.toPosition) {
                        viewModel.addRate()
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
                        setAdapters(it.data)
                    }

                    is ConvertCurrencyState.Error -> {
                        hideLoading()
                        Toast.makeText(activity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setAdapters(currencyRatesResponse: CurrencyRatesResponse? = null) {
        val currencyDataItemList = ArrayList<CurrencyDataItem>()
        currencyRatesResponse!!.rates.forEach {
            currencyDataItemList.add(CurrencyDataItem(name = it.key, rateValue = it.value))
        }

        val rateAdapter: ArrayAdapter<CurrencyDataItem> =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                currencyDataItemList
            )

        rateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.fromSpinner.adapter = rateAdapter
        binding.toSpinner.adapter = rateAdapter

        binding.fromEditText.setText("1")

        EspressoIdlingResource.decrement()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.from_spinner -> {
                viewModel.from = parent.selectedItem as CurrencyDataItem
                viewModel.fromPosition = position
            }

            R.id.to_spinner -> {
                viewModel.to = parent.selectedItem as CurrencyDataItem
                viewModel.toPosition = position
            }
        }

        if (viewModel.from.name.isNotEmpty()) {
            binding.toEditText.setText(viewModel.getConversionResult())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.swap_btn -> {
                binding.fromSpinner.setSelection(viewModel.toPosition)
                binding.fromEditText.setText(viewModel.toValue)
                binding.toSpinner.setSelection(viewModel.fromPosition)
                binding.toEditText.setText(viewModel.fromValue)
            }

            R.id.details_btn -> {
                val bundle = Bundle()
                bundle.putParcelableArrayList("PopularCurrencies", viewModel.popularList)

                viewModel.popularList.forEach {
                    Log.d("TAG", "initUI: ${it.name}: ${it.rateValue}")
                }
            }
        }
    }
}