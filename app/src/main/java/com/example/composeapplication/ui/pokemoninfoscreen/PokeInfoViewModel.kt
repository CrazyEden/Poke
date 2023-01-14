package com.example.composeapplication.ui.pokemoninfoscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.ui.model.PokemonInfoData
import com.example.composeapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    private val _pokeInfoFlow = MutableStateFlow<Resource<PokemonInfoData>>(Resource.Loading())
    val pokeInfoFlow = _pokeInfoFlow.asStateFlow()
    fun loadPokemonInfoData(pokemonId: Int) {
        viewModelScope.launch {
            val data = runCatching {
                val response = apiPokemonRepository.getOnePokemon(pokemonId)
                if (response is Resource.Success) {
                    val pokeInfo = PokemonInfoData.fromOnePokemonResponse(response.data!!)
                    Resource.Success(pokeInfo)
                } else Resource.Error(response.message!!)
            }.getOrElse {
                Resource.Error(it.message?: "VIEW MODEL EMPTY ERROR")
            }
            _pokeInfoFlow.value = data
        }
    }
}