package com.example.composeapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composeapplication.data.ItemsPagingSource
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.ui.model.ItemsListOneItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ItemsLiseViewModel @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
) : ViewModel() {



    /*
    *   api havent filter pokemon and to create new filtered flow requiere fun,
    *   but fun cant save state
    *   in order to avoid recreate the same flow has this fun
    */
    fun getPokeListPaging(filter: String): Flow<PagingData<ItemsListOneItemData>> {
        return if (flow != null && this.filter==filter) flow!!
        else createNewFlow(filter)
    }
    private var filter:String = "ёёёёё"
    private var flow: Flow<PagingData<ItemsListOneItemData>>? = null
    private fun createNewFlow(filter: String): Flow<PagingData<ItemsListOneItemData>> {
        this.filter = filter
        flow = Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            )
        ){
            ItemsPagingSource(
                apiPokemonRepository = apiPokemonRepository,
                filter = filter
            )
        }.flow.cachedIn(viewModelScope)
        return flow!!
    }
}