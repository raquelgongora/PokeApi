package org.raquel.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.raquel.pokedex.R;
import org.raquel.pokedex.adapters.DamageRelationAdapter;
import org.raquel.pokedex.adapters.PokemonAdapter;
import org.raquel.pokedex.entities.Pokemon;
import org.raquel.pokedex.entities.PokemonType;
import org.raquel.pokedex.interfaces.AsyncTaskHandler;
import org.raquel.pokedex.network.PokemonTypeAsyncTask;

import static org.raquel.pokedex.utils.Utils.getTypeImageResource;

public class PokemonTypeActivity extends AppCompatActivity implements AsyncTaskHandler, PokemonAdapter.ItemClickListener {

    ImageView cover, typeImage;
    TextView name;
    RecyclerView damageRelations;
    RecyclerView pokemons;
    PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_type);

        typeImage = findViewById(R.id.typeImage);

        cover = findViewById(R.id.type_cover);
        name = findViewById(R.id.type_name);
        damageRelations = findViewById(R.id.type_damage_relations);
        pokemons = findViewById(R.id.type_pokemons);

        String type = getIntent().getStringExtra("TYPE");
        String url = "https://pokeapi.co/api/v2/type/" + type;

        PokemonTypeAsyncTask pokemonTypeAsyncTask = new PokemonTypeAsyncTask();
        pokemonTypeAsyncTask.handler = this;
        pokemonTypeAsyncTask.execute(url);
    }

    @Override
    public void onTaskEnd(Object result) {
        PokemonType pokemonType = (PokemonType) result;



        name.setText(pokemonType.getName());
        typeImage.setImageResource(getTypeImageResource(pokemonType.getName()));

        damageRelations.setLayoutManager(new LinearLayoutManager(this));
        damageRelations.setAdapter(new DamageRelationAdapter(this, pokemonType));

        pokemons.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new PokemonAdapter(this, pokemonType.getPokemons());
        adapter.setClickListener(this);
        pokemons.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Pokemon pokemon = adapter.getPokemon(position);

        Intent intent = new Intent(this, PokemonDetailsActivity.class);
        intent.putExtra("URL", pokemon.getUrl());
        startActivity(intent);
    }


}
