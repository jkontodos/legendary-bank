package com.javierkontos.legendarybank.data.repository

import com.javierkontos.legendarybank.data.source.LocalDataSource
import com.javierkontos.legendarybank.data.source.RemoteDataSource
import javax.inject.Inject

class GoliathRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

}