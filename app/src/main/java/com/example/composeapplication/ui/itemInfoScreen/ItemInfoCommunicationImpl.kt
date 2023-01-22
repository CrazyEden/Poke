package com.example.composeapplication.ui.itemInfoScreen

import com.example.composeapplication.ui.model.ItemInfoData
import com.example.composeapplication.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ItemInfoCommunicationImpl : ItemInfoCommunication {
    private val flow = MutableStateFlow<Resource<ItemInfoData>>(Resource.Loading())

    override fun showData(data: Resource<ItemInfoData>) {
        flow.value = data
    }

    override fun getStateFlow(): StateFlow<Resource<ItemInfoData>> {
        return flow.asStateFlow()
    }
}