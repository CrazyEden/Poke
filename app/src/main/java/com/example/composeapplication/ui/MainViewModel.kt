package com.example.composeapplication.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.composeapplication.data.PokemonPagingSource
import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import javax.inject.Inject

class MainViewModel(
    private val apiPokemonRepository: ApiPokemonRepository
):ViewModel() {

    private val _pokemonLiveData = MutableLiveData<OnePokemonResponse>()
    val pokemonResponse:LiveData<OnePokemonResponse> = _pokemonLiveData

    val pokemonPaging by lazy {
        Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            )
        ){
            PokemonPagingSource(apiPokemonRepository)
        }.flow.cachedIn(viewModelScope)
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