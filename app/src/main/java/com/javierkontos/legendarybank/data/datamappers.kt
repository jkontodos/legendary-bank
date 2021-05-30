package com.javierkontos.legendarybank.data

import com.javierkontos.legendarybank.data.server.response.CurrencyRateResponse
import com.javierkontos.legendarybank.data.server.response.TransactionResponse
import com.javierkontos.legendarybank.domain.CurrencyRate
import com.javierkontos.legendarybank.domain.Transaction
import kotlin.math.roundToInt

fun List<CurrencyRateResponse>.toDomainCurrencyRateList(): List<CurrencyRate> =
    map { it.toDomainCurrencyRate() }

fun CurrencyRateResponse.toDomainCurrencyRate(): CurrencyRate =
    CurrencyRate(from, to, rate.toDouble())

fun List<TransactionResponse>.toDomainTransactionList(currencyRates: List<CurrencyRate>, localCurrency: String): List<Transaction> =
    map { it.toDomainTransaction(currencyRates, localCurrency) }

fun TransactionResponse.toDomainTransaction(currencyRates: List<CurrencyRate>, localCurrency: String): Transaction =
    Transaction(sku, amount.amountConversion(currencyRates, localCurrency, currency), localCurrency)

private fun String.amountConversion(
    currencyRates: List<CurrencyRate>,
    localCurrency: String,
    currentCurrency: String
): Double {
    var amountWithRate = 0.0
    var foundRate = false
    currencyRates.forEach {
        if (it.from == currentCurrency && it.to == localCurrency){
            amountWithRate = (this.toDouble()*it.rate)
            foundRate = true
        }
    }
    if (!foundRate){
        currencyRates.forEach {
            if (it.from == currentCurrency){
                amountWithRate = (this.toDouble()*it.rate)
                amountWithRate = amountWithRate.toString().amountConversion(currencyRates, localCurrency, it.to)
                return amountWithRate
            }
        }
    }

    return ((amountWithRate.toDouble() * 100.0).roundToInt() / 100.0)
}
