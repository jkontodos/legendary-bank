package com.javierkontos.legendarybank.data.sharedpreferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.javierkontos.legendarybank.data.source.LocalDataSource
import javax.inject.Inject

class SharedPreferencesDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : LocalDataSource{
    companion object {
        private const val CURRENCY_RATES = "currency_rates"
    }

    override fun saveCurrencyRates(currencyRates: String) {
        TODO("Not yet implemented")
    }

    override fun retrieveCurrencyRates(): String? {
        TODO("Not yet implemented")
    }

    override fun deleteCurrencyRates() {
        TODO("Not yet implemented")
    }
}