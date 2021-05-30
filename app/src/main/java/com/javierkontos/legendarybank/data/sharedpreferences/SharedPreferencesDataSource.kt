package com.javierkontos.legendarybank.data.sharedpreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.javierkontos.legendarybank.data.source.LocalDataSource
import com.javierkontos.legendarybank.domain.CurrencyRatesPreference
import javax.inject.Inject

class SharedPreferencesDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : LocalDataSource {
    companion object {
        private const val CURRENCY_RATES = "currency_rates"
    }

    override fun saveCurrencyRates(currencyRates: CurrencyRatesPreference) =
        sharedPreferences.edit(commit = true) {
            putString(CURRENCY_RATES, gson.toJson(currencyRates))
        }

    override fun retrieveCurrencyRates(): CurrencyRatesPreference? =
        sharedPreferences.getString(CURRENCY_RATES, null)?.let {
            gson.fromJson(it, CurrencyRatesPreference::class.java)
        }

    override fun deleteCurrencyRates() = sharedPreferences.edit(commit = true) {
        remove(CURRENCY_RATES).apply()
    }
}