package com.example.composeapplication.ui.itemsListScreen

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composeapplication.data.ItemsPagingSource
import com.example.composeapplication.domain.usecase.GetItemsPageUseCase
import com.example.composeapplication.ui.model.ItemsListOneItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemListCommunicationImpl @Inject constructor(
    private val getItemsPageUseCase: GetItemsPageUseCase
) : ItemsListCommunication {
    private var filter:String? = null
    private var flow: Flow<PagingData<ItemsListOneItemData>>? = null

    override fun getPagingFlow(scope: CoroutineScope, filter: String): Flow<PagingData<ItemsListOneItemData>> {
        return if (flow != null && this.filter==filter) flow!!
        else createNewFlow(filter,scope)
    }

    private fun createNewFlow(filter: String, scope: CoroutineScope): Flow<PagingData<ItemsListOneItemData>> {
        this.filter = filter
        flow = Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            )
        ){
            ItemsPagingSource(
                getItemsPageUseCase = getItemsPageUseCase,
                filter = filter
            )
        }.flow.cachedIn(scope)
        return flow!!
    }
}