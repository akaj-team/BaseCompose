package com.ync.basecompose.ui.features.detail

import androidx.lifecycle.bindError
import androidx.lifecycle.bindLoading
import androidx.lifecycle.viewModelScope
import com.ync.basecompose.arch.base.BaseViewModel
import com.ync.basecompose.arch.extentions.onSuccess
import com.ync.basecompose.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

/**
 * Created by mvn-cuongle-dn
 */
@HiltViewModel
class DetailCoinViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    BaseViewModel(), DetailCoinVMContract {

    val detailCoinUiViewState = MutableStateFlow(DetailUIViewState())

    override fun getDetailCoin(id: String) {
        searchRepository.getDetailCoin(id).bindLoading(this).bindError(this).onSuccess { response ->
            detailCoinUiViewState.value = DetailUIViewState(response)
        }.launchIn(viewModelScope)
    }
}
