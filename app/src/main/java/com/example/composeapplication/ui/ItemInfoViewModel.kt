package com.example.composeapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.ui.model.ItemInfoData
import com.example.composeapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemInfoViewModel @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository
) : ViewModel() {
    private val _flow = MutableStateFlow<Resource<ItemInfoData>>(Resource.Loading())
    val flow = _flow.asStateFlow()
    fun loadItemInfo(itemName: String) {
        viewModelScope.launch {
            val response = apiPokemonRepository.getOneItem(itemName)
            if (response is Resource.Success) {
                val itemInfoData = ItemInfoData.fromOneItemResponse(response.data!!)
                _flow.value = Resource.Success(itemInfoData)
            } else _flow.value = Resource.Error(response.message!!)
        }
    }
}