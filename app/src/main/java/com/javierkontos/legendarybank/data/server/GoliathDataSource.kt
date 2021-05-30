package com.javierkontos.legendarybank.data.server

import com.javierkontos.legendarybank.data.server.response.CurrencyRateResponse
import com.javierkontos.legendarybank.data.source.RemoteDataSource
import timber.log.Timber
import javax.inject.Inject

class GoliathDataSource @Inject constructor(
    private val goliathService: GoliathService
) : RemoteDataSource {
    override suspend fun getCurrencyRates(): List<CurrencyRateResponse>? = try {
        goliathService.getCurrencyRates()
    } catch (exception: Exception) {
        Timber.e(exception, "ApiError: ${exception.localizedMessage}")
        null
    }
}