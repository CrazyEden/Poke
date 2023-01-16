package com.example.composeapplication.ui.pokemoninfoscreen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composeapplication.R
import com.example.composeapplication.ui.model.PokemonInfoData
import com.example.composeapplication.util.Resource


@Composable
fun PokemonInfoScreen(
    pokeId: Int,
    vModel: PokeInfoViewModel
) {
    val data = vModel.pokeInfoFlow.collectAsState()
    Box(Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(15.dp)
        .verticalScroll(rememberScrollState())
        .offset(y=50.dp)
        .padding(bottom = 50.dp)
    ) {
        Log.d("xdd", "${data.value.data?.name} ${data.value.message}")
        when(data.value){
            is Resource.Success -> PokemonInfoScreenFieldState(data.value.data!!)
            is Resource.Loading -> PokemonInfoScreenLoadingState()
            is Resource.Error->{
                PokemonInfoScreenErrorState(data.value.message!!){
                    vModel.loadPokemonInfoData(pokeId)
                }
            }
        }
    }
}

@Composable
private fun PokemonInfoScreenFieldState(data: PokemonInfoData){
    Card(modifier = Modifier
        .fillMaxSize(),
        elevation = 4.dp
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                modifier = Modifier.size(200.dp),
                placeholder = painterResource(R.drawable.pokeball_place_holder),
                contentDescription = data.name,
                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${data.id}.png"
            )
            Text(
                text = data.name,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 25.sp,
                    shadow = Shadow(blurRadius = 1f)
                )
            )
            Spacer(modifier = Modifier.height( 20.dp))
            /* detailsShowingTypes */
            Row() {
                data.detailsShowingTypes.forEach {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .border(1.dp, Color.Red, RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            ,
                        text = it,
                        style = TextStyle(
                            fontSize = 18.sp,
                            shadow = Shadow(blurRadius = 1f),
                            color = Color.Black
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height( 20.dp))
            /* base stats */
            Text(
                text = "Базовые характеристики",
                style = TextStyle(fontSize = 15.sp,color = Color.Black)
            )
            Spacer(modifier = Modifier.height(5.dp))
            data.baseStats.forEach { (t, u) ->
                Box{
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .shadow(10.dp, RoundedCornerShape(8.dp)),
                        progress = u.toFloat()/100,
                        color = Color.Magenta
                    )
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 9.dp),
                        text = t.replaceFirstChar { it.uppercaseChar() },
                        textAlign = TextAlign.Start
                    )
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 9.dp),
                        text = "$u",
                        textAlign = TextAlign.End
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}

@Composable
private fun PokemonInfoScreenLoadingState(){
    Card(modifier = Modifier
        .fillMaxSize(),
        elevation = 15.dp
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(R.drawable.pokeball_place_holder),
                contentDescription = ""
            )
            Text(
                text = "",
                style = TextStyle(
                    fontSize = 25.sp,
                    shadow = Shadow(blurRadius = 1f)
                )
            )
            Spacer(modifier = Modifier.height( 20.dp))
            /* detailsShowingTypes */
            Row() {
                repeat(2) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .border(1.dp, Color.Red, RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .padding(horizontal = 40.dp),
                        text = "",
                        style = TextStyle(
                            fontSize = 18.sp,
                            shadow = Shadow(blurRadius = 1f)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height( 20.dp))
            /* base stats */
            Text(text = "Базовые характеристики", style = TextStyle(fontSize = 15.sp, color = Color.Black))
            Spacer(modifier = Modifier.height(5.dp))
            repeat(6) {
                Box{
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .shadow(10.dp, RoundedCornerShape(8.dp)),
                        color = Color.Magenta
                    )
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 9.dp),
                        text = "",
                        textAlign = TextAlign.Start,
                        style = TextStyle(color = Color.Black)
                    )
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 9.dp),
                        text = "",
                        textAlign = TextAlign.End,
                        style = TextStyle(color = Color.Black)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}

@Composable
fun PokemonInfoScreenErrorState(
    message:String,
    callback: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box{
            Button(onClick = { callback() })
            { Text(text = "Обновить", textAlign = TextAlign.Center) }
        }
        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Red, fontSize = 14.sp)
        )
    }
}
