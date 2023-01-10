package com.example.composeapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.composeapplication.appComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var factory: MainViewModel.Factory
    private val vModel by viewModels<MainViewModel>{ factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}
