package com.example.composeapplication.ui

import androidx.lifecycle.*
import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(
    private val apiPokemonRepository: ApiPokemonRepository
):ViewModel() {

    private val _pokemonLiveData = MutableLiveData<OnePokemonResponse>()
    val pokemonResponse:LiveData<OnePokemonResponse> = _pokemonLiveData

    fun loadPokemon(id:Int){
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
    class Factory @Inject constructor(
        private val apiPokemonRepository: ApiPokemonRepository
    ):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            modelClass.isAssignableFrom(MainViewModel::class.java)
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(apiPokemonRepository) as T
        }
    }
}