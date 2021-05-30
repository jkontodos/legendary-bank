package com.javierkontos.legendarybank.data.repository

import com.javierkontos.legendarybank.data.source.LocalDataSource
import com.javierkontos.legendarybank.data.source.RemoteDataSource
import com.javierkontos.legendarybank.data.toDomainCurrencyRateList
import com.javierkontos.legendarybank.data.toDomainTransactionList
import com.javierkontos.legendarybank.domain.CurrencyRate
import com.javierkontos.legendarybank.domain.CurrencyRatesPreference
import com.javierkontos.legendarybank.domain.Transaction
import javax.inject.Inject

class GoliathRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun getCurrencyRates(): List<CurrencyRate>? = remoteDataSource.getCurrencyRates()?.let {
        it.toDomainCurrencyRateList().apply {
            localDataSource.saveCurrencyRates(CurrencyRatesPreference(this))
        }
    }

    suspend fun getTransactions(): List<Transaction>? = remoteDataSource.getTransactions()?.let {
        it.toDomainTransactionList()
    }
}