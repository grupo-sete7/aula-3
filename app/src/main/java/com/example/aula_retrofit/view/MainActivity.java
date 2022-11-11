package com.example.aula_retrofit.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aula_retrofit.R;
import com.example.aula_retrofit.adapter.PokemonAdapter;
import com.example.aula_retrofit.domain.response.PokemonResponse;
import com.example.aula_retrofit.domain.response.ResultResponse;
import com.example.aula_retrofit.domain.service.PokemonService;
import com.example.aula_retrofit.model.Pokemon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String BASE_URL = "https://pokeapi.co/api/v2/";
    private RecyclerView mRecyclerView;
    private PokemonService pokemonService;
    private List<Pokemon> listPokedex;
    private PokemonAdapter mPokemonAdapter;
    private FloatingActionButton mFloatingActionButton;
    private ResultResponse mResultResponse;
    private int pageNumber = 0;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();

        configureRecyclerView();

        initRetrofit();

        getListPokemon(pageNumber);
    }

    private void initializeComponents() {
        mRecyclerView = findViewById(R.id.gv_pokemon);
    }

    private void configureRecyclerView() {
        mPokemonAdapter = new PokemonAdapter();
        mRecyclerView.setAdapter(mPokemonAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    int last = layoutManager.findLastCompletelyVisibleItemPosition();
                    if (layoutManager != null && last >= pageNumber * 20 - 1) {
                        Snackbar.make(mRecyclerView.getRootView(), "Carregando mais 20 pokemons", Snackbar.LENGTH_SHORT).show();
                        getListPokemon(pageNumber * 20);
                    }
                }
            }
        });
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.pokemonService = retrofit.create(PokemonService.class);
    }

    public void getListPokemon(int offset) {
        isLoading = true;
        mPokemonAdapter.setPageNumber(pageNumber++);
        Call<ResultResponse> resquestPokemon = pokemonService.getPokemon(offset, 20);
        resquestPokemon.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                mResultResponse = response.body();
                List<PokemonResponse> list = mResultResponse.getPokemonList();
                listPokedex = new ArrayList<>();
                for (PokemonResponse pokemon : list) {
                    Pokemon p = new Pokemon(pokemon.getName());
                    listPokedex.add(p);
                }
                initAdapter();
                isLoading = false;
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na busca de pokémon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAdapter() {
        mPokemonAdapter.setList(listPokedex);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mFloatingActionButton.getId()) {
            getListPokemon(pageNumber * 20);
        }
    }
}