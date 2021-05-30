package com.javierkontos.legendarybank.domain

data class Transaction(
    val sku: String,
    val amount: String,
    val currency: String
)