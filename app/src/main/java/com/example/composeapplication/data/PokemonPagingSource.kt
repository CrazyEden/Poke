package com.example.composeapplication.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.ui.PokemonListOneItemData
import com.example.composeapplication.util.Resource

class PokemonPagingSource(
    private val apiPokemonRepository: ApiPokemonRepository
) :PagingSource<Int,PokemonListOneItemData>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonListOneItemData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListOneItemData> {
        val page = params.key ?: 0
        return runCatching {
            val result = apiPokemonRepository.getPokemonPage(20, page*20)
            require(result is Resource.Success)
            val prevKey = if (page==0) null else page.minus(1)
            val nextKey = if (page*20 > result.data?.count!!) null else page.plus(1)
            val list = result.data.listOfPokemon.map {
                val id = it.url.drop(34).dropLast(1).toInt()
                PokemonListOneItemData(
                    id = id,
                    name = it.name,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                )
            }
            LoadResult.Page(
                data = list,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }.getOrElse {
            Log.wtf("xdd", "PokemonPagingSource | load: ",it )
            LoadResult.Error(it)
        }

    }
}