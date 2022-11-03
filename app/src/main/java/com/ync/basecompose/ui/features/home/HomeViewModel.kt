package com.ync.basecompose.ui.features.home

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
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    BaseViewModel(), HomeVMContract {
    val homeUiViewState = MutableStateFlow(HomeUiViewState())

    override fun getTrending() {
        searchRepository.getTrending()
            .bindLoading(this)
            .bindError(this)
            .onSuccess { response ->
                homeUiViewState.value = HomeUiViewState(item = response.coins.map { it.item })
            }.launchIn(viewModelScope)
    }

}