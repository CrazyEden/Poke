package com.example.composeapplication

import androidx.lifecycle.*
import com.example.composeapplication.onePokemonResponse.OnePokemonResponse
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
            return MainViewModel(apiPokemonRepository) as T
        }
    }
}