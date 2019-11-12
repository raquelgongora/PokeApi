package org.sara.pokedex.entities;

public class PokemonDetails {

    private String name;
    private int id;
    private int baseExperience;
    private int weight;
    private String[] type;
    private String url;

    public PokemonDetails(String name, int id, int baseExperience, int weight, String[] type, String url) {
        this.name = name;
        this.url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + id + ".png";
        this.id = id;
        this.baseExperience = baseExperience;
        this.weight = weight;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getBaseexperience() {
        return baseExperience;
    }

    public int getWeight() {
        return weight;
    }

    public String[] getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBaseexperience(int baseexperience) {
        this.baseExperience = baseexperience;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setType(String[] type) {
        this.type = type;
    }

}