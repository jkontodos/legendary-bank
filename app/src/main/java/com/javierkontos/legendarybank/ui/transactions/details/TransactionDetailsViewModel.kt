package com.javierkontos.legendarybank.ui.transactions.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javierkontos.legendarybank.data.usecases.GetTransactionsUseCase
import com.javierkontos.legendarybank.domain.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class TransactionDetailsViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {
    private var _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> = _model

    fun getTransactionDetails(productSku: String, transactionList: List<Transaction>) {
        _model.value = UiModel.Loading
        handleSuccess(transactionList.filter { it.sku == productSku })
    }

    private fun handleFailure() {
        _model.value = UiModel.Failure
    }

    private fun handleSuccess(transactionList: List<Transaction>) {
        _model.value = UiModel.Success(transactionList)
    }

    fun getTotal(transactionList: List<Transaction>): String {
        var total = 0.0
        transactionList.forEach {
            total += it.amount
        }
        total = ((total * 100.0).roundToInt() / 100.0)
        return total.toString()
    }

    sealed class UiModel {
        object Loading : UiModel()
        object Failure : UiModel()
        data class Success(val transactionList: List<Transaction>) : UiModel()
    }
}