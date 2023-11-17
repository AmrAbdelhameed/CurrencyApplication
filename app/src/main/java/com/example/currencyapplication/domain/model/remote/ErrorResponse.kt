package com.example.currencyapplication.domain.model.remote

data class ErrorResponse(
    val error: Error,
    val success: Boolean
)