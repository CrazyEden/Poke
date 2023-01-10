package com.example.composeapplication

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
interface BindsModule {

    @Binds
    fun provideApiPokemonRepImpl(apiPokemonRepositoryImpl: ApiPokemonRepositoryImpl):ApiPokemonRepository

    @Binds
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ):ApiPokemon = retrofit.create(ApiPokemon::class.java)
}