package com.example.composeapplication.domain.usecase

import com.example.composeapplication.domain.model.itemsPage.ItemsPage
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.util.Resource
import javax.inject.Inject

class GetItemsPageUseCase @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
){
    suspend fun execute(limit:Int,offset:Int): Resource<ItemsPage> {
        if (limit < 1) throw IllegalArgumentException()
        if (offset<0) throw IllegalArgumentException()
        return apiPokemonRepository.getItemsPage(limit, offset)
    }
}