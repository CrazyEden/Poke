package com.example.composeapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeapplication.ui.pokemoninfoscreen.PokeInfoViewModel
import com.example.composeapplication.ui.pokemoninfoscreen.PokemonInfoScreen
import com.example.composeapplication.ui.pokemonlistscreen.PokeListScreen
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    @Inject
//    lateinit var factory: PokeListModel.Factory
//    @Inject
//    lateinit var factory2: PokeInfoViewModel.Factory.Factoryy

    override fun onCreate(savedInstanceState: Bundle?) {
//        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                val hostController = rememberNavController()
                NavHost(
                    navController = hostController,
                    startDestination = "poke_list"
                ){
                    composable(route = "poke_list"){
                        PokeListScreen(
                            navController = hostController
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
                        val vModel:PokeInfoViewModel = hiltViewModel()
                        vModel.loadPokemonInfoData(pokeId)
                        PokemonInfoScreen(
                            navControler = hostController,
                            pokeId = pokeId,
                            vModel = vModel
                        )
                    }
                }
            }
        }
    }
}
