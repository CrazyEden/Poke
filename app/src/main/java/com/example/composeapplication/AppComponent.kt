package com.example.composeapplication

import dagger.Component

@Component(modules = [RetrofitModule::class,BindsModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}