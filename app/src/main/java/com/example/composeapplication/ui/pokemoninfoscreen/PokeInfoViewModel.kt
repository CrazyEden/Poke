package com.example.composeapplication.ui.pokemoninfoscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.domain.usecase.GetOnePokemonUseCase
import com.example.composeapplication.ui.model.PokemonInfoData
import com.example.composeapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeInfoViewModel @Inject constructor(
    private val getOnePokemonUseCase: GetOnePokemonUseCase,
    private val pokeInfoCommunication: PokeInfoCommunication
) : ViewModel() {

    fun getFlow() = pokeInfoCommunication.getStateFlow()

    fun loadPokemonInfoData(pokemonId: Int) {
        viewModelScope.launch {
            val data = runCatching {
                val response = getOnePokemonUseCase.execute(pokemonId)
                if (response is Resource.Success) {
                    val pokeInfo = PokemonInfoData.fromOnePokemonResponse(response.data!!)
                    Resource.Success(pokeInfo)
                } else Resource.Error(response.message!!)
            }.getOrElse {
                Resource.Error(it.message?: "VIEW MODEL EMPTY ERROR")
            }
            pokeInfoCommunication.showData(data)
        }
    }
}