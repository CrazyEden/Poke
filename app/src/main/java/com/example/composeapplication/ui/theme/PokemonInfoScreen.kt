package com.example.composeapplication.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.composeapplication.R
import com.example.composeapplication.ui.PokeInfoViewModel
import com.example.composeapplication.ui.PokemonInfoData
import com.example.composeapplication.util.Resource

@Composable
fun PokemonInfoScreen(
    pokeId: Int
) {
    val vModel: PokeInfoViewModel = hiltViewModel()
    val data = produceState<Resource<PokemonInfoData>>(initialValue = Resource.Loading()){
        val tempData = vModel.getPokemonInfoData(pokeId).data!!
        val pokeInfoData = PokemonInfoData.fromOnePokemonResponse(tempData)
        value = Resource.Success(pokeInfoData)
    }.value
    Box(Modifier
        .fillMaxSize()
    ) {


            if (pokeId == 1) PokemonInfoScreenLoadingState()
            else if (pokeId == 2) PokemonInfoScreenErrorState {

            }
            else
            when(data){
                is Resource.Success->{
                    PokemonInfoScreenFieldState(data.data!!)
                }
                is Resource.Loading->{
                    PokemonInfoScreenLoadingState()
                }
                is Resource.Error->{
                    PokemonInfoScreenErrorState{
                        TODO()
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
    callback: () -> Unit
){
    Button(
        onClick = { callback() }
    ) {
        Text(text = "Обновить", textAlign = TextAlign.Center)
    }
}
