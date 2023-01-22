package com.example.composeapplication.ui.itemsListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsLiseViewModel @Inject constructor(
    private val itemsListCommunication: ItemsListCommunication
) : ViewModel() {

    fun getPokeListPaging(filter: String) =
        itemsListCommunication.getPagingFlow(scope = viewModelScope, filter = filter)

}