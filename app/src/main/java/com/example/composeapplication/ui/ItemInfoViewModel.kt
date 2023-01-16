package com.example.composeapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemInfoViewModel @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
) : ViewModel() {
    fun loadItemInfo(itemName: String) {
        viewModelScope.launch {
            apiPokemonRepository.getOneItem(itemName)
        }
    }
}