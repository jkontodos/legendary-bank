package com.javierkontos.legendarybank.data.server.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @Expose
    @SerializedName("sku")
    val sku: String,
    @Expose
    @SerializedName("amount")
    val amount: String,
    @Expose
    @SerializedName("currency")
    val currency: String
)