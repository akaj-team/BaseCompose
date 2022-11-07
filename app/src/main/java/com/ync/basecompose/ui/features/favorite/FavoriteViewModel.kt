package com.ync.basecompose.ui.features.favorite

import androidx.lifecycle.bindError
import androidx.lifecycle.bindLoading
import androidx.lifecycle.viewModelScope
import com.ync.basecompose.arch.base.BaseViewModel
import com.ync.basecompose.arch.extentions.onSuccess
import com.ync.basecompose.data.repository.ExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

/**
 * @author mvn-toan.nguyen2 on 11/7/22
 **/
@HiltViewModel
class FavoriteViewModel @Inject constructor(private val exchangeRepository: ExchangeRepository) :
    BaseViewModel(), FavoriteVMContract {
    companion object {
        private const val PER_PAGE = 10
    }

    val favoriteUiViewState = MutableStateFlow(FavoriteUIViewState())
    private var currentPage = 1
    override fun getExchanges(isLoadMore: Boolean) {
        exchangeRepository.getExchanges(PER_PAGE, currentPage)
            .bindLoading(this)
            .bindError(this)
            .onSuccess { response ->
                currentPage++
                favoriteUiViewState.value.item.addAll(response)
            }.launchIn(viewModelScope)
    }
}
