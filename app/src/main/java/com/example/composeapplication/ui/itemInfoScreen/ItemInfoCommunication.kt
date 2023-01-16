package com.example.composeapplication.ui.itemInfoScreen

import com.example.composeapplication.ui.model.ItemInfoData
import com.example.composeapplication.util.Resource
import kotlinx.coroutines.flow.StateFlow

interface ItemInfoCommunication {

    fun showData(data: Resource<ItemInfoData>)

    fun getStateFlow() : StateFlow<Resource<ItemInfoData>>

}