package org.raquel.pokedex.network;



import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.raquel.pokedex.modelos.PokemonDetails;
import org.raquel.pokedex.interfaces.AsyncTaskHandler;

import java.io.IOException;
import java.net.URL;

import static org.raquel.pokedex.network.NetworkUtils.createUrl;
import static org.raquel.pokedex.network.NetworkUtils.makeHttpRequest;

public class PokemonDetailsAsyncTask extends AsyncTask<String, Void, PokemonDetails> {

    public AsyncTaskHandler handler;

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected PokemonDetails doInBackground(String... urls) {

        URL url = createUrl(urls[0]);
        // Hacemos la petición. Ésta puede tirar una exepción.
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
            return pokemonDetails(jsonResponse);
        } catch (IOException e) {
            Log.e("Download error", "Problem making the HTTP request.", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(PokemonDetails pokemonDetails) {
        super.onPostExecute(pokemonDetails);
        if (handler != null) {
            handler.onTaskEnd(pokemonDetails);
        }
    }

    private PokemonDetails pokemonDetails(String jsonStr) {
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int baseExperience = jsonObj.getInt("base_experience");
            String name = jsonObj.getString("name");
            int weight = jsonObj.getInt("weight");
            int height = jsonObj.getInt("height");
            int id = jsonObj.getInt("id");

            JSONArray jsonArray = jsonObj.getJSONArray("types");
            String[] types = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                String type = jsonArray.getJSONObject(i).getJSONObject("type").getString("name");
                types[i] = type;
            }
            PokemonDetails pokemonDetails = new PokemonDetails(name, id, baseExperience, weight, height, types);
            return pokemonDetails;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}