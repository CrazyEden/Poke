package com.example.composeapplication.ui.itemInfoScreen

import com.example.composeapplication.ui.model.ItemInfoData
import com.example.composeapplication.util.Resource
import junit.framework.TestCase.assertEquals
import org.junit.Test


internal class ItemInfoCommunicationImplTest{

    @Test
    fun startState(){
        val communication = ItemInfoCommunicationImpl()
        val flow = communication.getStateFlow().value
        assertEquals(true, flow is Resource.Loading)
    }
    @Test
    fun successRequest(){
        val communication = ItemInfoCommunicationImpl()
        communication.showData(Resource.Success(
            ItemInfoData(
                name = "xdd", category = "покебол", imageUrl = "https//", effects = listOf()
            )
        ))
        val expected = ItemInfoData(
            name = "xdd", category = "покебол", imageUrl = "https//", effects = listOf()
        )
        val actual = communication.getStateFlow().value
        assertEquals(true, actual is Resource.Success)
        assertEquals(expected,actual.data)
    }
    @Test
    fun errorRequest(){
        val communication = ItemInfoCommunicationImpl()
        communication.showData(Resource.Error("xdd"))
        val expected = "xdd"
        val actual = communication.getStateFlow().value
        assertEquals(true, actual is Resource.Error)
        assertEquals(expected,actual.message)
    }

}