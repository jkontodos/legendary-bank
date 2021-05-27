package com.javierkontos.legendarybank.data.di

import android.content.Context
import android.content.SharedPreferences
import com.javierkontos.legendarybank.data.server.GoliathDataSource
import com.javierkontos.legendarybank.data.sharedpreferences.SharedPreferencesDataSource
import com.javierkontos.legendarybank.data.source.LocalDataSource
import com.javierkontos.legendarybank.data.source.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideSharedPrefences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("goliath_pref", Context.MODE_PRIVATE)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsDataModule {

    @Binds
    abstract fun bindsLocalDataSource(sharedPreferencesDataSource: SharedPreferencesDataSource): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(goliathDataSource: GoliathDataSource): RemoteDataSource
}