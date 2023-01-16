package com.example.composeapplication.ui.itemInfoScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.ui.model.ItemInfoData
import com.example.composeapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemInfoViewModel @Inject constructor(
    private val apiPokemonRepository: ApiPokemonRepository,
    private val itemInfoCommunication: ItemInfoCommunication
) : ViewModel() {
    fun getItemInfoFlow() = itemInfoCommunication.getStateFlow()
    fun loadItemInfo(itemName: String) {
        viewModelScope.launch {
            val response = apiPokemonRepository.getOneItem(itemName)
            if (response is Resource.Success) {
                val itemInfoData = ItemInfoData.fromOneItemResponse(response.data!!)
                itemInfoCommunication.showData(Resource.Success(itemInfoData))
            } else itemInfoCommunication.showData(Resource.Error(response.message!!))
        }
    }
}