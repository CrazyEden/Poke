package com.example.composeapplication.ui.model

import com.example.composeapplication.domain.model.oneItemResponse.OneItemResponse

data class ItemInfoData(
    val name:String,
    val category: String,
    val imageUrl:String,
    val effects:List<String>
){
    companion object{
        fun fromOneItemResponse(oneItemResponse: OneItemResponse) : ItemInfoData{
            return ItemInfoData(
                name = oneItemResponse.name!!.replace("-", " "),
                category = oneItemResponse.category!!.name!!,
                imageUrl = oneItemResponse.sprites!!.default!!,
                effects = oneItemResponse.effectEntries?.map { it.effect!! }!!
            )
        }
    }
}