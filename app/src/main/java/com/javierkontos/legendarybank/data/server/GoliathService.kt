package com.javierkontos.legendarybank.data.server

import com.javierkontos.legendarybank.data.server.response.CurrencyRateResponse
import com.javierkontos.legendarybank.data.server.response.TransactionResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface GoliathService {
    @GET("rates")
    @Headers("Accept: application/json")
    suspend fun getCurrencyRates(): List<CurrencyRateResponse>

    @GET("transactions")
    @Headers("Accept: application/json")
    suspend fun getTransactions(): List<TransactionResponse>
}
