package com.example.composeapplication.domain.usecase

import com.example.composeapplication.FakeRepository
import com.example.composeapplication.domain.model.pokemonPage.PokemonPage
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


internal class GetPokemonPageUseCaseTest{
    private val useCase = GetPokemonPageUseCase(FakeRepository())

    @Test
    fun successRequest(){
        runBlocking {
            val actual = useCase.execute(20,40).data
            val expected = PokemonPage()
            assertEquals(expected, actual)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun incorrectLimit(){
        runBlocking {
            useCase.execute(0,40)
        }
    }
    @Test(expected = IllegalArgumentException::class)
    fun incorrectOffset(){
        runBlocking {
            useCase.execute(20,-1)
        }
    }
    @Test(expected = IllegalArgumentException::class)
    fun incorrectOffsetAndLimit(){
        runBlocking {
            useCase.execute(-123,-1)
        }
    }
}