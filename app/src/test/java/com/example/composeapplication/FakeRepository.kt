package com.example.composeapplication

import com.example.composeapplication.domain.model.itemsPage.ItemsPage
import com.example.composeapplication.domain.model.oneItemResponse.OneItemResponse
import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse
import com.example.composeapplication.domain.model.pokemonPage.PokemonPage
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.util.Resource

internal class FakeRepository() : ApiPokemonRepository {
    override suspend fun getOnePokemon(id: Int): Resource<OnePokemonResponse> {
        return Resource.Success(OnePokemonResponse(id=id))
    }

    override suspend fun getPokemonPage(limit: Int, offset: Int): Resource<PokemonPage> {
        return Resource.Success(PokemonPage())
    }

    override suspend fun getOneItem(name: String): Resource<OneItemResponse> {
        return Resource.Success(OneItemResponse(name = name))
    }

    override suspend fun getItemsPage(limit: Int, offset: Int): Resource<ItemsPage> {
        return Resource.Success(ItemsPage())
    }

}