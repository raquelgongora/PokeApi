package org.sara.pokedex.entities;

public class PokemonDetails {

    private String name;
    private int id;
    private int baseExperience;
    private int weight;
    private String[] type;
    private String image;

    public PokemonDetails(String name, int id, int baseExperience, int weight, String[] type) {
        this.name = name;
        this.id = id;
        this.baseExperience = baseExperience;
        this.weight = weight;
        this.type = type;
        this.image = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + id + ".png";
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public int getWeight() {
        return weight;
    }

    public String[] getType() {
        return type;
    }

    public String getImage() {
        return image;
    }
}