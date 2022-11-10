package com.example.aula_retrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.aula_retrofit.R;
import com.example.aula_retrofit.adapter.PokemonAdapter;
import com.example.aula_retrofit.domain.response.PokemonResponse;
import com.example.aula_retrofit.domain.response.ResultResponse;
import com.example.aula_retrofit.domain.service.PokemonService;
import com.example.aula_retrofit.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://pokeapi.co/api/v2/";
    private GridView mGridView;
    private PokemonService pokemonService;
    private List<Pokemon> listPokedex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mGridView = findViewById(R.id.gv_pokemon);
        initRetrofit();
        getListPokemon(this);
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.pokemonService = retrofit.create(PokemonService.class);
    }

    public void getListPokemon(Context context) {
        Call<ResultResponse> resquestPokemon = pokemonService.getPokemon();
        resquestPokemon.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse repository = response.body();
                List<PokemonResponse> list = repository.getPokemonList();
                listPokedex = new ArrayList<>();
                for (PokemonResponse pokemon: list){
                    Pokemon p = new Pokemon(pokemon.getName());
                    listPokedex.add(p);
                }
                initAdapter();
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, "Erro na busca de pokémon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAdapter() {
        PokemonAdapter adapter = new PokemonAdapter(this, listPokedex);
        this.mGridView.setAdapter(adapter);
    }
}