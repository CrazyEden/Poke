package com.example.composeapplication

import com.example.composeapplication.di.BindsModule
import com.example.composeapplication.di.RetrofitModule
import com.example.composeapplication.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, BindsModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}