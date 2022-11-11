package com.example.aula_retrofit.domain.service;

import com.example.aula_retrofit.domain.response.ResultResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <i>Created by alexalins on 09/11/2022</i>
 *
 * @author Alexa Lins <alexa.lins@capgemini.com>
 */
public interface PokemonService {
    @GET(value = "pokemon/")
    Call<ResultResponse> getPokemon(@Query("offset") int offset, @Query("limit") int limit);
}
