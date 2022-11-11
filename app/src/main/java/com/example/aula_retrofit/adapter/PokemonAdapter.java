package com.example.aula_retrofit.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aula_retrofit.R;
import com.example.aula_retrofit.Utils.PokemonUtils;
import com.example.aula_retrofit.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * <i>Created by alexalins on 10/11/2022</i>
 *
 * @author Alexa Lins <alexa.lins@capgemini.com>
 */
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private List<Pokemon> list = new ArrayList<>();

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    private int pageNumber;

    public PokemonAdapter() {
        super();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.bind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setList(List<Pokemon> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
        private View view;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            mImageView = itemView.findViewById(R.id.image_pokemon);
            mTextView = itemView.findViewById(R.id.pokemon_name);
        }

        public void bind(Pokemon pokemon, int position){
            mTextView.setText(pokemon.getName());
            Glide.with(view).load(PokemonUtils.getUrlMyPokemon(++position)).into(mImageView);
        }
    }

}
