package com.example.currencyapplication.domain.model.remote

data class Error(
    val code: Int,
    val info: String,
    val type: String
)
