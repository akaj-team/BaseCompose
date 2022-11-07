package com.ync.basecompose.ui.features.favorite

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ync.basecompose.data.model.Exchanges

/**
 * @author mvn-toan.nguyen2 on 11/7/22
 **/
data class FavoriteUIViewState(
    var item: SnapshotStateList<Exchanges> = mutableStateListOf(),
)
