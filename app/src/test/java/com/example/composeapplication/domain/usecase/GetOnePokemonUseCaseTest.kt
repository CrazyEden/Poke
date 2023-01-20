package com.example.composeapplication.domain.usecase

import com.example.composeapplication.FakeRepository
import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


internal class GetOnePokemonUseCaseTest{
    val useCase = GetOnePokemonUseCase(FakeRepository())

    @Test
    fun successRequest(){
        runBlocking {
            val actual = useCase.execute(123).data
            val expected = OnePokemonResponse(id=123)
            assertEquals(expected,actual)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun incorrectId(){
        runBlocking {
            useCase.execute(-1)
        }
    }
}