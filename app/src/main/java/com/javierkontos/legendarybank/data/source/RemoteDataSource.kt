package com.javierkontos.legendarybank.data.source

import com.javierkontos.legendarybank.data.server.response.CurrencyRateResponse

interface RemoteDataSource {
    suspend fun getCurrencyRates(): List<CurrencyRateResponse>?

}