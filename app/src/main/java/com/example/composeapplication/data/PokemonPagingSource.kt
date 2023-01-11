package com.example.composeapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composeapplication.domain.model.pokemonPage.ShortPokemonInfo
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.util.Resource

class PokemonPagingSource(
    private val apiPokemonRepository: ApiPokemonRepository
) :PagingSource<Int,ShortPokemonInfo>() {
    override fun getRefreshKey(state: PagingState<Int, ShortPokemonInfo>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShortPokemonInfo> {
        val page = params.key ?: 0
        return runCatching {
            val result = apiPokemonRepository.getPokemonPage(20, page*20)
            require(result is Resource.Success)
            val prevKey = if (page==0) null else page.minus(1)
            val nextKey = if (page*20 > result.data?.count!!) null else page.plus(1)
            val list = result.data.listOfPokemon
            LoadResult.Page(
                data = list,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }.getOrElse {
            LoadResult.Error(it)
        }

    }
}