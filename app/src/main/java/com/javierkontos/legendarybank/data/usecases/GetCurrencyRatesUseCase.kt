package com.javierkontos.legendarybank.data.usecases

import com.javierkontos.legendarybank.data.repository.GoliathRepository
import com.javierkontos.legendarybank.domain.CurrencyRate
import javax.inject.Inject

class GetCurrencyRatesUseCase @Inject constructor(
    private val goliathRepository: GoliathRepository
) {
    suspend fun invoke(): List<CurrencyRate>? =
        goliathRepository.getCurrencyRates()
}