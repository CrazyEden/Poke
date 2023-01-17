package com.example.composeapplication.ui.pokemonlistscreen

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composeapplication.data.PokemonPagingSource
import com.example.composeapplication.domain.usecase.GetPokemonPageUseCase
import com.example.composeapplication.ui.model.PokemonListOneItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeListCommunicationImpl @Inject constructor(
    private val getPokemonPageUseCase: GetPokemonPageUseCase
) : PokeListCommunication {
    private var filter:String? = null
    private var flow: Flow<PagingData<PokemonListOneItemData>>? = null

    override fun getPagingFlow(scope:CoroutineScope,filter: String): Flow<PagingData<PokemonListOneItemData>> {
        return if (flow != null && this.filter==filter) flow!!
        else createNewFlow(filter,scope)
    }

    private fun createNewFlow(filter: String, scope: CoroutineScope): Flow<PagingData<PokemonListOneItemData>> {
        this.filter = filter
        flow = Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            )
        ){
            PokemonPagingSource(
                getPokemonPageUseCase = getPokemonPageUseCase,
                filter = filter
            )
        }.flow.cachedIn(scope)
        return flow!!
    }
}