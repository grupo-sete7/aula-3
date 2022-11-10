package com.example.aula_retrofit.Utils;

/**
 * <i>Created by alexalins on 10/11/2022</i>
 *
 * @author Alexa Lins <alexa.lins@capgemini.com>
 */
public class PokemonUtils {

    private static final String BASE_IMAGE = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/";

    public static String getUrlMyPokemon(int id) {
        if(id > 0) {
            return BASE_IMAGE + formatNumber(id) + ".png";
        }

        return "";
    }

    private static String formatNumber(int id) {
        String idString = String.valueOf(id);
        if(id < 10) {
            return  "00" + idString;
        } else if (id < 100) {
            return "0" + idString;
        }
        return idString;
    }
}
