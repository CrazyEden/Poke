package com.example.composeapplication.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composeapplication.data.PokemonPagingSource
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
):ViewModel() {
    init {
        Log.w("xdd", "MainViewModel INIT: ", )
    }

    override fun onCleared() {
        Log.w("xdd", "MainViewModel CLEARED", )
        super.onCleared()
    }

    /*
    *   api havent filter pokemon and to create new filtered flow requiere fun,
    *   but fun cant save state
    *   in order to avoid recreate the same flow has this fun
    */
    fun getPokeListPaging(filter: String): Flow<PagingData<PokemonListOneItemData>> {
        return if (flow != null && this.filter==filter) flow!!
        else createNewFlow(filter)
    }
    private var filter:String = "ёёёёё"
    private var flow: Flow<PagingData<PokemonListOneItemData>>? = null
    private fun createNewFlow(filter: String): Flow<PagingData<PokemonListOneItemData>> {
        this.filter = filter
        flow = Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            )
        ){
            PokemonPagingSource(
                apiPokemonRepository = apiPokemonRepository,
                filter = filter
            )
        }.flow.cachedIn(viewModelScope)
        return flow!!
    }

}