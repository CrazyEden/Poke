package com.example.composeapplication.domain.usecase

import com.example.composeapplication.FakeRepository
import com.example.composeapplication.domain.model.itemsPage.ItemsPage
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class GetItemsPageUseCaseTest{

    private val useCase = GetItemsPageUseCase(FakeRepository())
    @Test
    fun successRequest(){
        runBlocking {
            val actual = useCase.execute(20,40).data
            val expected = ItemsPage()
            assertEquals(actual,expected)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun incorrectLimit(){
        runBlocking {
            useCase.execute(0,40).data
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun incorrectOffset(){
        runBlocking {
            useCase.execute(20,-1).data
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun incorrectOffsetAnd(){
        runBlocking {
            useCase.execute(-1,-1).data
        }
    }
}