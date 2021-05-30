package com.javierkontos.legendarybank.data.source

import com.javierkontos.legendarybank.data.server.response.CurrencyRateResponse
import com.javierkontos.legendarybank.data.server.response.TransactionResponse

interface RemoteDataSource {
    suspend fun getCurrencyRates(): List<CurrencyRateResponse>?
    suspend fun getTransactions(): List<TransactionResponse>?

}