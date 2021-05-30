package com.javierkontos.legendarybank.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javierkontos.legendarybank.data.usecases.GetTransactionsUseCase
import com.javierkontos.legendarybank.domain.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {
    private var _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> = _model

    fun getTransactions() {
        _model.value = UiModel.Loading
        viewModelScope.launch {
            getTransactionsUseCase.invoke()?.let {
                handleSuccess(it)
            } ?: handleFailure()
        }
    }

    private fun handleFailure() {
        _model.value = UiModel.Failure
    }

    private fun handleSuccess(transactionList: List<Transaction>) {
        _model.value = UiModel.Success(transactionList)
    }

    sealed class UiModel {
        object Loading : UiModel()
        object Failure : UiModel()
        data class Success(val transactionList: List<Transaction>) : UiModel()
    }
}