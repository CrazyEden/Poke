package com.example.composeapplication.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composeapplication.domain.usecase.GetPokemonPageUseCase
import com.example.composeapplication.ui.model.PokemonListOneItemData
import com.example.composeapplication.util.Resource

class PokemonPagingSource(
    private val getPokemonPageUseCase: GetPokemonPageUseCase,
    private val filter:String,
    private val aCountItemsOnOnePage:Int = 20
) :PagingSource<Int, PokemonListOneItemData>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonListOneItemData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListOneItemData> {
        val page = params.key ?: 0
        return runCatching {
            val result = getPokemonPageUseCase.execute(
                limit = aCountItemsOnOnePage,
                offset = page * aCountItemsOnOnePage
            )
            require(result is Resource.Success && result.data != null)
            val prevKey = if (page==0) null else page.minus(1)
            val nextKey = if (page*20 > result.data.count!!) null else page.plus(1)
            val list = result.data.listOfPokemon.map {
                val id = it.url.drop(34).dropLast(1).toInt()
                PokemonListOneItemData(
                    id = id,
                    name = it.name,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                )
            }
            if (filter == "")
                return LoadResult.Page(
                data = list,
                prevKey = prevKey,
                nextKey = nextKey
            )
            val filteredList = mutableListOf<PokemonListOneItemData>()
            list.forEach {
                if (it.name.contains(filter))
                    filteredList.add(it)
            }
            LoadResult.Page(
                data = filteredList,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }.getOrElse {
            Log.wtf("xdd", "PokemonPagingSource | load: ",it )
            LoadResult.Error(it)
        }

    }
}