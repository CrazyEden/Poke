package com.example.composeapplication.domain.usecase

import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import javax.inject.Inject

class GetItemsPageUseCase @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
){
    suspend fun execute(limit:Int,offset:Int) = apiPokemonRepository.getItemsPage(limit,offset)
}