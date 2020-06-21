package com.br.btgteste.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.btgteste.domain.model.ApiResult
import com.br.btgteste.domain.model.Currency
import com.br.btgteste.domain.usecase.CurrencyListUseCase

class ListCurrencyViewModel(private val currencyListUseCase: CurrencyListUseCase): ViewModel() {

    val liveDataResponse: MutableLiveData<ApiResult<List<Currency>>> = MutableLiveData()

    fun getCurrencies(){
        currencyListUseCase { response ->
            if (response is ApiResult.Success) {
                val sortedList = response.data.sortedBy { it.name }
                response.data = sortedList
            }
            liveDataResponse.value = response
        }
    }

    fun performFiltering(query: CharSequence, currenciesList: List<Currency>): List<Currency> {
        val filteredList = currenciesList.subList(0, currenciesList.size)
        return filteredList.filter {
            it.code.startsWith(query, true) || it.name.startsWith(query,true) }
    }
}