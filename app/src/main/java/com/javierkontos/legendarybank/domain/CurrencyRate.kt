package com.javierkontos.legendarybank.domain

data class CurrencyRate(
    val from: String,
    val to: String,
    val rate: String
)

data class CurrencyRatesPreference(
    val currencyRates: List<CurrencyRate>
)