package com.example.composeapplication.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokeInfoViewModel @Inject constructor (
    private val apiPokemonRepository: ApiPokemonRepository
    ): ViewModel() {
    init {
        Log.d("xdd", "PokeInfoViewModel INIT")
    }
    override fun onCleared() {
        Log.d("xdd", "PokeInfoViewModel CLEARED")
        super.onCleared()
    }
    suspend fun getPokemonInfoData(pokemonId:Int): Resource<OnePokemonResponse> {
        return apiPokemonRepository.getOnePokemon(pokemonId)
    }
}