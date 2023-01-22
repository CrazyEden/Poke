package com.example.composeapplication.ui.pokemoninfoscreen

import com.example.composeapplication.ui.model.PokemonInfoData
import com.example.composeapplication.util.Resource
import junit.framework.TestCase
import org.junit.Test


internal class PokeInfoCommunicationImplTest{
    @Test
    fun startState(){
        val communication = PokeInfoCommunicationImpl()
        val flow = communication.getStateFlow().value
        TestCase.assertEquals(true, flow is Resource.Loading)
    }
    @Test
    fun successRequest(){
        val communication = PokeInfoCommunicationImpl()
        communication.showData(
            Resource.Success(
            PokemonInfoData(
                id = 21, name = "picka","http///", listOf(), mapOf()
            )
        ))
        val expected = PokemonInfoData(
            id = 21, name = "picka","http///", listOf(), mapOf()
        )
        val actual = communication.getStateFlow().value
        TestCase.assertEquals(true, actual is Resource.Success)
        TestCase.assertEquals(expected, actual.data)
    }
    @Test
    fun errorRequest(){
        val communication = PokeInfoCommunicationImpl()
        communication.showData(Resource.Error("xdd"))
        val expected = "xdd"
        val actual = communication.getStateFlow().value
        TestCase.assertEquals(true, actual is Resource.Error)
        TestCase.assertEquals(expected, actual.message)
    }
}