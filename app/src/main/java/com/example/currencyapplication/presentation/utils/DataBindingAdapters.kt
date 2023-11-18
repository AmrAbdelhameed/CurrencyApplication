package com.example.currencyapplication.presentation.utils

import android.R
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapplication.presentation.base.BaseAdapter
import com.example.currencyapplication.presentation.base.ListAdapterItem
import com.example.currencyapplication.presentation.ui.convert_currency.CurrencyDataItem
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseAdapter<ViewDataBinding, ListAdapterItem>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.updateData(list ?: listOf())
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["items", "label"], requireAll = true)
fun submitList(lineChart: LineChart, items: List<Double>?, label: String) {
    val lineValues = ArrayList<Entry>()
    var yAxis = 0.0f

    for (item in items ?: listOf()) {
        lineValues.add(Entry(yAxis, item.toFloat()))
        yAxis += 1F
    }

    val lineDataSet = LineDataSet(lineValues, label)
    lineDataSet.circleRadius = 10f
    lineDataSet.setDrawFilled(true)
    lineDataSet.valueTextSize = 14F
    lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

    val data = LineData(lineDataSet)

    lineChart.data = data
    lineChart.animateXY(2000, 2000, Easing.EaseInCubic)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(appCompatSpinner: AppCompatSpinner, list: List<CurrencyDataItem>?) {
    val arrayAdapter = ArrayAdapter(
        appCompatSpinner.context,
        R.layout.simple_spinner_item,
        list ?: arrayListOf()
    )
    arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
    appCompatSpinner.adapter = arrayAdapter
}

@BindingAdapter("manageState")
fun manageState(progressBar: ProgressBar, state: Boolean) {
    progressBar.visibility = if (state) View.VISIBLE else View.GONE
}