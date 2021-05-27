package com.javierkontos.legendarybank.data.source

import com.javierkontos.legendarybank.domain.CurrencyRatesPreference

interface LocalDataSource {
    // Currency Rates preference
    fun saveCurrencyRates(currencyRates: CurrencyRatesPreference)
    fun retrieveCurrencyRates(): CurrencyRatesPreference?
    fun deleteCurrencyRates()
}
