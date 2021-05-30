package com.javierkontos.legendarybank.data.usecases

import com.javierkontos.legendarybank.data.repository.GoliathRepository
import com.javierkontos.legendarybank.domain.Transaction
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val goliathRepository: GoliathRepository
) {
    suspend fun invoke(): List<Transaction>? =
        goliathRepository.getTransactions()
}