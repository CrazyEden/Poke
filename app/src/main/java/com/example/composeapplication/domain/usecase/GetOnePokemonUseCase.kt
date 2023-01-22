package com.example.composeapplication.domain.usecase

import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.util.Resource
import javax.inject.Inject

class GetOnePokemonUseCase @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
) {
    suspend fun execute(id:Int): Resource<OnePokemonResponse> {
        if (id<0) throw IllegalArgumentException()
        return apiPokemonRepository.getOnePokemon(id)
    }
}