package org.sara.pokedex.entities;

public class Pokemon {
    private String id;
    private String name;
    private String url;
    private String image;

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;

        this.id = url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "");

        this.image = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + id + ".png";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }
}
