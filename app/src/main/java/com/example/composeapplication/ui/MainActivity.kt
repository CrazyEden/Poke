package com.example.composeapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composeapplication.appComponent
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import com.example.composeapplication.ui.theme.PokeListScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var factory: MainViewModel.Factory
    private val vModel by viewModels<MainViewModel>{ factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            Toast.makeText(this,"",Toast.LENGTH_LONG).show()
            ComposeApplicationTheme(){
                val hostController = rememberNavController()
                NavHost(
                    navController = hostController,
                    startDestination = "poke_list"
                ){
                    composable(route = "poke_list"){
                        PokeListScreen(
                            navController = hostController,
                            vModel.pokemonPaging.collectAsLazyPagingItems()
                        )
                    }
                    composable(
                        route = "poke_info/{pokemonId}",
                        arguments = listOf(
                            navArgument("pokemonId"){
                                type = NavType.IntType
                                nullable = false
                            }
                        )
                    ){
                        val pokeId = it.arguments?.getInt("pokemonId") ?: throw NullPointerException("required id")
                    }
                }
            }
        }
    }
}
