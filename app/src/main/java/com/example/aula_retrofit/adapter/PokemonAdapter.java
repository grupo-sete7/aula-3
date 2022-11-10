package com.example.aula_retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.aula_retrofit.R;
import com.example.aula_retrofit.Utils.PokemonUtils;
import com.example.aula_retrofit.model.Pokemon;

import java.util.List;

/**
 * <i>Created by alexalins on 10/11/2022</i>
 *
 * @author Alexa Lins <alexa.lins@capgemini.com>
 */
public class PokemonAdapter extends ArrayAdapter<Pokemon> {

    private List<Pokemon> list;

    public PokemonAdapter(@NonNull Context context, @NonNull List<Pokemon> objects) {
        super(context, 0, objects);
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pokemon pokemon = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pokemon, parent, false);
        }
        //
        TextView textName = convertView.findViewById(R.id.pokemon_name);
        textName.setText(pokemon.getName());
        //
        ImageView imageView = convertView.findViewById(R.id.image_pokemon);
        Glide.with(convertView).load(PokemonUtils.getUrlMyPokemon(position + 1)).into(imageView);
        //
        return convertView;
    }
}
