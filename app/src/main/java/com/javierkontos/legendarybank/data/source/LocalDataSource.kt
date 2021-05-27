package com.javierkontos.legendarybank.data.source

interface LocalDataSource {
    // Currency Rates preference
    fun saveCurrencyRates(currencyRates: String)
    fun retrieveCurrencyRates(): String?
    fun deleteCurrencyRates()
}
