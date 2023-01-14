package com.example.composeapplication.ui.pokemoninfoscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.composeapplication.R
import com.example.composeapplication.ui.model.PokemonInfoData
import com.example.composeapplication.util.Resource


@Composable
fun PokemonInfoScreen(
    navControler: NavHostController,
    pokeId: Int,
    vModel: PokeInfoViewModel
) {
    val data = vModel.pokeInfoFlow.collectAsState()
    Box(Modifier
        .fillMaxSize()
    ) {
        Log.d("xdd", "${data.value.data?.name} ${data.value.message}")
        when(data.value){
            is Resource.Success->{
                PokemonInfoScreenFieldState(data.value.data!!)
            }
            is Resource.Loading->{
                PokemonInfoScreenLoadingState()
            }
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
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
        .padding(top = 50.dp)
        .padding(15.dp)
        .border(2.dp, Color.DarkGray, RoundedCornerShape(5.dp))
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
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
                            shadow = Shadow(blurRadius = 1f)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height( 20.dp))
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
                        text = t,
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
    CircularProgressIndicator()

}

@Composable
private fun PokemonInfoScreenErrorState(
    message:String,
    callback: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
