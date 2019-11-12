package org.sara.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import org.sara.pokedex.adapters.PokemonAdapter;
import org.sara.pokedex.entities.Pokemon;
import org.sara.pokedex.network.JsonAsyncTask;

import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonAsyncTask.JsonAsyncHandler, PokemonAdapter.ItemClickListener {

    PokemonAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_pokemon);

        JsonAsyncTask jsonAsyncTask = new JsonAsyncTask();
        jsonAsyncTask.handler = this;
        jsonAsyncTask.execute();

    }

    @Override
    public void onPokemonsDownloaded(List<Pokemon> pokemons) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new PokemonAdapter(this, pokemons);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Pokemon pokemon = adapter.getPokemon(position);
    }
}
