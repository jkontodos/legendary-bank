package com.javierkontos.legendarybank.data.server

import com.javierkontos.legendarybank.data.server.response.CurrencyRateResponse
import com.javierkontos.legendarybank.data.server.response.TransactionResponse
import retrofit2.http.GET

interface GoliathService {
    @GET("rates.json")
    suspend fun getCurrencyRates(): List<CurrencyRateResponse>

    @GET("transactions.json")
    suspend fun getTransactions(): List<TransactionResponse>
}
