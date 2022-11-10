package com.example.aula_retrofit.domain.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <i>Created by alexalins on 09/11/2022</i>
 *
 * @author Alexa Lins <alexa.lins@capgemini.com>
 */
public class ResultResponse {

    @SerializedName(value = "results")
    private List<PokemonResponse> pokemonList;

    public ResultResponse() {
    }

    public ResultResponse(List<PokemonResponse> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public List<PokemonResponse> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<PokemonResponse> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
