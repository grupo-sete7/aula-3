package com.example.aula_retrofit.domain.response;

import com.google.gson.annotations.SerializedName;

/**
 * <i>Created by alexalins on 09/11/2022</i>
 *
 * @author Alexa Lins <alexa.lins@capgemini.com>
 */
public class PokemonResponse {

    @SerializedName(value = "name")
    private String name;

    public PokemonResponse(String name) {
        this.name = name;
    }

    public PokemonResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
