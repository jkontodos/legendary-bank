package com.javierkontos.legendarybank.data.server

import com.javierkontos.legendarybank.data.server.response.CurrencyRateResponse
import retrofit2.http.GET

interface GoliathService {
    @GET("rates.json")
    suspend fun getCurrencyRates(): List<CurrencyRateResponse>

}
