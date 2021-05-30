package com.javierkontos.legendarybank.data

import com.javierkontos.legendarybank.data.server.response.CurrencyRateResponse
import com.javierkontos.legendarybank.data.server.response.TransactionResponse
import com.javierkontos.legendarybank.domain.CurrencyRate
import com.javierkontos.legendarybank.domain.Transaction

fun List<CurrencyRateResponse>.toDomainCurrencyRateList(): List<CurrencyRate> =
    map { it.toDomainCurrencyRate() }

fun CurrencyRateResponse.toDomainCurrencyRate(): CurrencyRate =
    CurrencyRate(from, to, rate)

fun List<TransactionResponse>.toDomainTransactionList(): List<Transaction> =
    map { it.toDomainTransaction() }

fun TransactionResponse.toDomainTransaction(): Transaction =
    Transaction(sku, amount, currency)