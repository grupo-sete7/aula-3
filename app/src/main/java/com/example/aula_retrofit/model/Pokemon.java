package com.example.aula_retrofit.model;

/**
 * <i>Created by alexalins on 09/11/2022</i>
 *
 * @author Alexa Lins <alexa.lins@capgemini.com>
 */
public class Pokemon {
    private String name;

    public Pokemon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                '}';
    }
}
