package com.example.currencyapplication.presentation.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.example.currencyapplication.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffDecimal(): Double {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toDouble()
}

fun Int?.orEmpty(default: Int = 0): Int {
    return this ?: default
}

fun NavController.navigateSafe(@IdRes resId: Int, args: Bundle? = null) {
    val destinationId = currentDestination?.getAction(resId)?.destinationId.orEmpty()
    currentDestination?.let { node ->
        val currentNode = when (node) {
            is NavGraph -> node
            else -> node.parent
        }
        if (destinationId != 0) {
            currentNode?.findNode(destinationId)?.let { navigate(resId, args) }
        }
    }
}

fun LineChart.displayLineChartData(items: List<Double>) {
    val lineValues = ArrayList<Entry>()
    var yAxis = 0.0f

    for (item in items) {
        lineValues.add(Entry(yAxis, item.toFloat()))
        yAxis += 1F
    }

    val lineDataSet = LineDataSet(lineValues, this.context?.getString(R.string.graph_label))
    lineDataSet.circleRadius = 10f
    lineDataSet.setDrawFilled(true)
    lineDataSet.valueTextSize = 20F
    lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

    val data = LineData(lineDataSet)

    this.data = data
    this.animateXY(2000, 2000, Easing.EaseInCubic)
}