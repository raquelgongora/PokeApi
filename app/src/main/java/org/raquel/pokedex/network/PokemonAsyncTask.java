package org.raquel.pokedex.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.raquel.pokedex.modelos.Pokemon;
import org.raquel.pokedex.interfaces.AsyncTaskHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.raquel.pokedex.network.NetworkUtils.createUrl;
import static org.raquel.pokedex.network.NetworkUtils.makeHttpRequest;

 public class PokemonAsyncTask extends AsyncTask<Void, Void, List<Pokemon>> {

    public AsyncTaskHandler handler;

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Pokemon> doInBackground(Void... voids) {

        URL url = createUrl("https://pokeapi.co/api/v2/pokemon?limit=100&offset=151");
        // Hacemos la petición. Ésta puede tirar una exepción.
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
            return listapokemon(jsonResponse);
        } catch (IOException e) {
            Log.e("Download error", "Problem making the HTTP request.", e);
        }
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<Pokemon> pokemonList) {
        super.onPostExecute(pokemonList);
        if (handler != null) {
            handler.onTaskEnd(pokemonList);
        }
    }

     private List<Pokemon> listapokemon(String jsonStr) {
         try {
             JSONObject jsonObj = new JSONObject(jsonStr);
             JSONArray jsonArray = jsonObj.getJSONArray("results");
             ArrayList<Pokemon> pokemonShortList = new ArrayList<>();
             for (int i = 0; i < jsonArray.length(); i++) {
                 String url = jsonArray.getJSONObject(i).getString("url");
                 String name = jsonArray.getJSONObject(i).getString("name");
                 pokemonShortList.add(new Pokemon(name, url));
             }
             return pokemonShortList;
         } catch (JSONException e) {
             e.printStackTrace();
         }
         return null;
     }
}