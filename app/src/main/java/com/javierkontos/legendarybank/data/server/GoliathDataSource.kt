package com.javierkontos.legendarybank.data.server

import com.javierkontos.legendarybank.data.source.RemoteDataSource
import javax.inject.Inject

class GoliathDataSource @Inject constructor(
    private val goliathService: GoliathService
) : RemoteDataSource {
}