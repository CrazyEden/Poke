package com.example.composeapplication.ui.itemsListScreen

import androidx.paging.PagingData
import com.example.composeapplication.ui.model.ItemsListOneItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ItemsListCommunication {

    fun getPagingFlow(scope: CoroutineScope, filter: String): Flow<PagingData<ItemsListOneItemData>>
}