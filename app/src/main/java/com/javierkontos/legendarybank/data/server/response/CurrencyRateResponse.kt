package com.javierkontos.legendarybank.data.server.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyRateResponse(
    @Expose
    @SerializedName("from")
    val from: String,
    @Expose
    @SerializedName("to")
    val to: String,
    @Expose
    @SerializedName("rate")
    val rate: String
)