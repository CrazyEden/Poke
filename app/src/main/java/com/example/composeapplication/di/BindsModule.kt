package com.example.composeapplication.di

import com.example.composeapplication.data.repositories.ApiPokemonRepositoryImpl
import com.example.composeapplication.domain.repositories.ApiPokemonRepository
import com.example.composeapplication.ui.itemInfoScreen.ItemInfoCommunication
import com.example.composeapplication.ui.itemInfoScreen.ItemInfoCommunicationImpl
import com.example.composeapplication.ui.itemsListScreen.ItemListCommunicationImpl
import com.example.composeapplication.ui.itemsListScreen.ItemsListCommunication
import com.example.composeapplication.ui.pokemoninfoscreen.PokeInfoCommunication
import com.example.composeapplication.ui.pokemoninfoscreen.PokeInfoCommunicationImpl
import com.example.composeapplication.ui.pokemonlistscreen.PokeListCommunication
import com.example.composeapplication.ui.pokemonlistscreen.PokeListCommunicationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface BindsModule {

    @Binds
    @ViewModelScoped
    fun provideApiPokemonRepImpl(apiPokemonRepositoryImpl: ApiPokemonRepositoryImpl):ApiPokemonRepository

    @Binds
    @ViewModelScoped
    fun providePokeVMCommunicator(pokeInfoCommunicationImpl: PokeInfoCommunicationImpl):PokeInfoCommunication

    @Binds
    @ViewModelScoped
    fun providePokeListCommunicator(pokeListCommunicationImpl: PokeListCommunicationImpl):PokeListCommunication

    @Binds
    @ViewModelScoped
    fun provideItemInfoCommunicator(itemInfoCommunicationImpl: ItemInfoCommunicationImpl):ItemInfoCommunication

    @Binds
    @ViewModelScoped
    fun provideItemsListCommunicator(itemsListCommunicationImpl: ItemListCommunicationImpl): ItemsListCommunication
}