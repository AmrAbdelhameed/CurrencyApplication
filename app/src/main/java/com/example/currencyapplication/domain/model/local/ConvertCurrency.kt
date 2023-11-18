package com.example.currencyapplication.domain.model.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "convert_currency",
    indices = [Index(
        value = ["fromName", "fromValue", "toName", "toValue", "createdAt"],
        unique = true
    )]
)
data class ConvertCurrency(
    val fromName: String,
    val fromValue: Double,
    val toName: String,
    val toValue: Double,
    val createdAt: Date,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)