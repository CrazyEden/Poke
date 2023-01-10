package com.example.composeapplication.data.remote

import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse
import com.example.composeapplication.domain.model.pokemonPage.PokemonPage
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