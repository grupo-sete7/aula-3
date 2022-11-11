package com.example.aula_retrofit.Utils;

/**
 * <i>Created by alexalins on 10/11/2022</i>
 *
 * @author Alexa Lins <alexa.lins@capgemini.com>
 */
public class PokemonUtils {

    private static final String BASE_IMAGE = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/";

    public static String getUrlMyPokemon(int id) {
        return id > 0 ? String.format("%s%03d.png", BASE_IMAGE, id) : "";
    }
}
