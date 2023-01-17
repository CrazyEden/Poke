package com.example.composeapplication.domain.usecase

import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import javax.inject.Inject

class GetOneItemUseCase @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
) {
    suspend fun execute(name:String) = apiPokemonRepository.getOneItem(name)
}