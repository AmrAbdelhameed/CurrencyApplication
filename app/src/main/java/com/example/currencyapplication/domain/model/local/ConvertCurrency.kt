package com.example.currencyapplication.domain.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "convert_currency")
data class ConvertCurrency(
    val fromName: String,
    val fromValue: Double,
    val toName: String,
    val toValue: Double,
    val createdAt: Date,
    @PrimaryKey(autoGenerate = true)
    val currencyId: Int = 0
)