package com.example.composeapplication.di

import com.example.composeapplication.data.repositories.ApiPokemonRepositoryImpl
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {

    @Binds
    fun provideApiPokemonRepImpl(apiPokemonRepositoryImpl: ApiPokemonRepositoryImpl): ApiPokemonRepository

}