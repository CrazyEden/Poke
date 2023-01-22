package com.example.composeapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeapplication.R
import com.example.composeapplication.ui.itemInfoScreen.ItemInfoScreen
import com.example.composeapplication.ui.itemInfoScreen.ItemInfoViewModel
import com.example.composeapplication.ui.itemsListScreen.ItemsListScreen
import com.example.composeapplication.ui.pokemoninfoscreen.PokeInfoViewModel
import com.example.composeapplication.ui.pokemoninfoscreen.PokemonInfoScreen
import com.example.composeapplication.ui.pokemonlistscreen.PokeListScreen
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                val hostController = rememberNavController()
                Column(Modifier.fillMaxSize()) {
                    NavHost(
                        modifier = Modifier.weight(1f),
                        navController = hostController,
                        startDestination = "poke_list"
                    ){
                        composable(route = "poke_list"){ PokeListScreen(navController = hostController) }
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
                                pokeId = pokeId,
                                vModel = vModel
                            )
                        }
                        composable(route ="item_list"){ ItemsListScreen(hostController = hostController) }
                        composable(route ="item_info/{itemName}", arguments = listOf(
                            navArgument("itemName"){
                                type = NavType.StringType
                                nullable = false
                            }
                        )){
                            val itemName = it.arguments?.getString("itemName") ?: throw NullPointerException()
                            val vModel: ItemInfoViewModel = hiltViewModel()
                            vModel.loadItemInfo(itemName)
                            ItemInfoScreen(
                                itemName = itemName,
                                vModel = vModel
                            )
                        }
                    }
                    BMenu(navHostController = hostController)
                }
            }
        }

    }
}

@Composable
private fun BMenu(
    navHostController: NavHostController
){
    val backStack = navHostController.currentBackStackEntryAsState()
    BottomNavigation {
        val isPokeTapSelected = backStack.value?.destination?.route == "poke_list"
        BottomNavigationItem(
            selected = isPokeTapSelected,
            onClick = {
                navHostController.navigate(route = "poke_list"){ popUpTo(0) }
            },
            icon = {
                BadgedBox(
                    modifier = Modifier,
                    content = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.pikachu),
                                contentDescription = "",
                                tint= Color.Unspecified
                            )
                            if (isPokeTapSelected)
                                Text(text = "Покемоны", style = TextStyle(fontSize = 10.sp))
                        }
                    },
                    badge = {}
                )
            }
        )

        val isItemTabSelected = backStack.value?.destination?.route == "item_list"
        BottomNavigationItem(
            selected = isItemTabSelected,
            onClick = {
                navHostController.navigate(route = "item_list"){ popUpTo(0) }
            },
            icon = {
                BadgedBox(
                    modifier = Modifier,
                    content = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.pokeball),
                                contentDescription = "",
                                tint= Color.Unspecified
                            )
                            if (isItemTabSelected)
                                Text(text = "Предметы", style = TextStyle(fontSize = 10.sp))
                        }
                    },
                    badge = {}
                )
            }
        )
    }
}
