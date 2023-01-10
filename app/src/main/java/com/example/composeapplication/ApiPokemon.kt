package com.example.composeapplication

import com.example.composeapplication.onePokemonResponse.OnePokemonResponse
import com.example.composeapplication.pokemonPage.PokemonPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPokemon {
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id:Int): Response<OnePokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") name:String): Response<OnePokemonResponse>

    @GET("pokemon")
    suspend fun getPokemonPage(
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ):Response<PokemonPage>
}