package com.ync.basecompose.ui.features.home

import com.ync.basecompose.data.model.Item

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
data class HomeUiViewState(
    var item: List<Item> = mutableListOf(),
)