package com.example.composeapplication.data.repositories

import com.example.composeapplication.data.remote.ApiPokemon
import com.example.composeapplication.domain.model.itemsPage.ItemsPage
import com.example.composeapplication.domain.model.oneItemResponse.OneItemResponse
import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse
import com.example.composeapplication.domain.model.pokemonPage.PokemonPage
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.util.Resource
import javax.inject.Inject

class ApiPokemonRepositoryImpl @Inject constructor(
    private val apiPokemon: ApiPokemon
): ApiPokemonRepository {
    override suspend fun getOnePokemon(id:Int): Resource<OnePokemonResponse> {
        return runCatching {
            val response = apiPokemon.getPokemon(id)
            if (response.isSuccessful)
                Resource.Success(response.body()!!)
            else Resource.Error(response.errorBody().toString())
        }.getOrElse {
            Resource.Error(it.message.toString())
        }
    }

    override suspend fun getPokemonPage(limit: Int, offset: Int): Resource<PokemonPage> {
        return runCatching {
            val response = apiPokemon.getPokemonPage(limit = limit, offset =  offset)
            if (response.isSuccessful)
                Resource.Success(response.body()!!)
            else Resource.Error(response.errorBody().toString())
        }.getOrElse {
            Resource.Error(it.message.toString())
        }
    }

    override suspend fun getOneItem(name: String): Resource<OneItemResponse> {
        return runCatching {
            val response = apiPokemon.getItem(name)
            if (response.isSuccessful)
                Resource.Success(response.body()!!)
            else Resource.Error(response.errorBody().toString())
        }.getOrElse {
            Resource.Error(it.message.toString())
        }
    }

    override suspend fun getItemsPage(limit: Int, offset: Int): Resource<ItemsPage> {
        return runCatching {
            val response = apiPokemon.getItemsPage(limit = limit, offset =  offset)
            if (response.isSuccessful)
                Resource.Success(response.body()!!)
            else Resource.Error(response.errorBody().toString())
        }.getOrElse {
            Resource.Error(it.message.toString())
        }
    }
}