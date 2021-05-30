package com.javierkontos.legendarybank.domain

data class CurrencyRate(
    val from: String,
    val to: String,
    val rate: Double
)

data class CurrencyRatesPreference(
    val currencyRates: List<CurrencyRate>
)