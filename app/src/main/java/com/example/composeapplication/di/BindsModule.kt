package com.example.composeapplication.di

import com.example.composeapplication.data.repositories.ApiPokemonRepositoryImpl
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    @Singleton
    fun provideApiPokemonRepImpl(apiPokemonRepositoryImpl: ApiPokemonRepositoryImpl): ApiPokemonRepository

}