package com.example.composeapplication.domain.usecase

import com.example.composeapplication.domain.model.pokemonPage.PokemonPage
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.util.Resource
import javax.inject.Inject

class GetPokemonPageUseCase @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
) {
    suspend fun execute(limit:Int,offset:Int): Resource<PokemonPage> = apiPokemonRepository.getPokemonPage(limit,offset)
}