package com.example.composeapplication.ui

import com.example.composeapplication.domain.model.onePokemonResponse.OnePokemonResponse

data class PokemonInfoData(
    val id:Int,
    val name:String,
    val imageUrl:String,
    val detailsShowingTypes:List<String>,
    val baseStats:Map<String,Int>
){
    companion object{
        fun fromOnePokemonResponse(onePokemonResponse: OnePokemonResponse):PokemonInfoData{

            return PokemonInfoData(
                id = onePokemonResponse.id,
                name = onePokemonResponse.name,
                imageUrl = onePokemonResponse.sprites.frontDefault,
                detailsShowingTypes = onePokemonResponse.types.map { it.type.name },
                baseStats = onePokemonResponse.stats.associate { it.stat.name to it.baseStat }
            )
        }
    }
}
