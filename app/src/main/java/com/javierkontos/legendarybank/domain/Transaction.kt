package com.javierkontos.legendarybank.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaction(
    val sku: String,
    val amount: Double,
    val currency: String
): Parcelable

@Parcelize
data class TransactionList(
    val transactions: List<Transaction>
): Parcelable