package com.example.composeapplication.di

import com.example.composeapplication.ui.itemInfoScreen.ItemInfoCommunicationImpl
import com.example.composeapplication.ui.pokemoninfoscreen.PokeInfoCommunicationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CommunicatorsModule {

    @Provides
    fun providePokeInfoCommunicator(): PokeInfoCommunicationImpl = PokeInfoCommunicationImpl()

    @Provides
    fun provideItemInfoCommunicator(): ItemInfoCommunicationImpl = ItemInfoCommunicationImpl()
}