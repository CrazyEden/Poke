package com.example.composeapplication.domain.usecase

import com.example.composeapplication.FakeRepository
import com.example.composeapplication.domain.model.oneItemResponse.OneItemResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


internal class GetOneItemUseCaseTest{
    val useCase = GetOneItemUseCase(FakeRepository())
    @Test
    fun successRequest(){
        runBlocking {
            val name = "picachoo"
            val actual = useCase.execute(name).data
            val expected = OneItemResponse(name = name)
            assertEquals(expected,actual)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun emptyName() {
        runBlocking {
            val name = ""
            useCase.execute(name).data
        }
    }
    @Test(expected = IllegalArgumentException::class)
    fun blankName() {
        runBlocking {
            val name = "\t"
            useCase.execute(name).data
        }
    }
}