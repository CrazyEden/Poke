package com.example.composeapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composeapplication.domain.usecase.GetItemsPageUseCase
import com.example.composeapplication.ui.model.ItemsListOneItemData
import com.example.composeapplication.util.Resource

class ItemsPagingSource (
    private val getItemsPageUseCase: GetItemsPageUseCase,
    private val filter:String,
    private val itemsOnPage:Int = 20
) : PagingSource<Int,ItemsListOneItemData>() {
    override fun getRefreshKey(state: PagingState<Int, ItemsListOneItemData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemsListOneItemData> {
        val page = params.key ?: 0
        return runCatching {
            val res = getItemsPageUseCase.execute(itemsOnPage, page * itemsOnPage)
            require(res is Resource.Success && res.data!=null &&res.data.results !=null)
            val prevKey = if (page==0) null else page.minus(1)
            val nextKey = if (page*20 > res.data.count!!) null else page.plus(1)
            val list:List<ItemsListOneItemData> = res.data.results.map {
                ItemsListOneItemData(
                    nameForSearch = it?.name!!,
                    nameTitle = it.name.replace("-"," "),
                    imageUrl ="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/${it.name}.png"
                )
            }
            if (filter.isEmpty()) {
                LoadResult.Page(
                    data = list,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                val filteredList = mutableListOf<ItemsListOneItemData>()
                list.forEach { if (it.nameTitle.contains(filter)) filteredList.add(it) }
                LoadResult.Page(
                    data = filteredList,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }

        }.getOrElse {
            LoadResult.Error(it)
        }
    }
}