package com.javierkontos.legendarybank.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javierkontos.legendarybank.data.usecases.GetCurrencyRatesUseCase
import com.javierkontos.legendarybank.domain.CurrencyRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase
) : ViewModel() {
    private var _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> = _model

    fun getCurrencyRates() {
        _model.value = UiModel.Loading
        viewModelScope.launch {
            getCurrencyRatesUseCase.invoke()?.let {
                handleSuccess(it)
            } ?: handleFailure()
        }
    }

    private fun handleFailure() {
        _model.value = UiModel.Failure
    }

    private fun handleSuccess(currencyRateList: List<CurrencyRate>) {
        _model.value = UiModel.Success(currencyRateList)
    }

    sealed class UiModel {
        object Loading : UiModel()
        object Failure : UiModel()
        data class Success(val currencyRateList: List<CurrencyRate>) : UiModel()
    }
}