package com.javierkontos.legendarybank.data.di

import com.javierkontos.legendarybank.data.server.GoliathDataSource
import com.javierkontos.legendarybank.data.source.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindRemoteDataSource(goliathDataSource: GoliathDataSource): RemoteDataSource
}