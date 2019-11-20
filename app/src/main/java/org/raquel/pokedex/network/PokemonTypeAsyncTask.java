package org.raquel.pokedex.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.raquel.pokedex.modelos.Pokemon;
import org.raquel.pokedex.modelos.PokemonType;
import org.raquel.pokedex.interfaces.AsyncTaskHandler;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.raquel.pokedex.network.NetworkUtils.createUrl;
import static org.raquel.pokedex.network.NetworkUtils.makeHttpRequest;


public class PokemonTypeAsyncTask extends AsyncTask<String, Void, PokemonType> {

    public AsyncTaskHandler handler;

    @Override
    protected PokemonType doInBackground(String... urls) {
        URL url = createUrl(urls[0]);
        // Hacemos la petición. Ésta puede tirar una exepción.
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
            return parsePokemonType(jsonResponse);
        } catch (IOException e) {
            Log.e("Download error", "Problem making the HTTP request.", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(PokemonType pokemonType) {
        super.onPostExecute(pokemonType);
        if (handler != null) {
            handler.onTaskEnd(pokemonType);
        }
    }

    private PokemonType parsePokemonType(String jsonStr) {
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String name = jsonObj.getString("name");
            JSONObject damageRelationsJson = jsonObj.getJSONObject("damage_relations");
            Map<String, List<String>> damageRelations = new HashMap<>();

            for (int i = 0; i < PokemonType.relationNames.length; i++) {
                List<String> typeNameList = new ArrayList<>();
                JSONArray jsonArray = damageRelationsJson.getJSONArray(PokemonType.relationNames[i]);

                for (int j = 0; j < jsonArray.length(); j++) {
                    String typeName = jsonArray.getJSONObject(j).getString("name");
                    typeNameList.add(typeName);
                }

                damageRelations.put(PokemonType.relationNames[i], typeNameList);
            }

            List<Pokemon> pokemons = new ArrayList<>();
            JSONArray pokemonsArray = jsonObj.getJSONArray("pokemon");
            int iterations = pokemonsArray.length() < 5 ? pokemonsArray.length() : 5;
            for (int i = 0; i < iterations; i ++) {
                JSONObject pokemonJson = pokemonsArray.getJSONObject(i).getJSONObject("pokemon");
                String url = pokemonJson.getString("url");
                String pokemonName = pokemonJson.getString("name");
                pokemons.add(new Pokemon(pokemonName, url));
            }

            PokemonType pokemonType = new PokemonType(name, damageRelations, pokemons);
            return pokemonType;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}