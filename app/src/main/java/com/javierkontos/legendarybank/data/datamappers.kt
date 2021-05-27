package com.javierkontos.legendarybank.data

import com.javierkontos.legendarybank.data.server.response.CurrencyRateResponse
import com.javierkontos.legendarybank.domain.CurrencyRate

fun List<CurrencyRateResponse>.toDomainCurrencyRateList(): List<CurrencyRate> =
    map { it.toDomainCurrencyRate() }

fun CurrencyRateResponse.toDomainCurrencyRate(): CurrencyRate =
    CurrencyRate(from, to, rate)