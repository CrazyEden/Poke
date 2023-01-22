package com.example.composeapplication.domain.usecase

import com.example.composeapplication.domain.model.oneItemResponse.OneItemResponse
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.util.Resource
import javax.inject.Inject

class GetOneItemUseCase @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
) {
    suspend fun execute(name:String): Resource<OneItemResponse> {
        if (name.isEmpty() || name.isBlank()) throw IllegalArgumentException()
        return apiPokemonRepository.getOneItem(name)
    }
}