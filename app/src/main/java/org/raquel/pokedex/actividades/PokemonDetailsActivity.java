package org.raquel.pokedex.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.raquel.pokedex.R;
import org.raquel.pokedex.adaptadores.RowTypesAdapter;
import org.raquel.pokedex.baseDatos.AppDatabase;
import org.raquel.pokedex.modelos.Pokemon;
import org.raquel.pokedex.modelos.PokemonDetails;
import org.raquel.pokedex.interfaces.AsyncTaskHandler;
import org.raquel.pokedex.network.PokemonDetailsAsyncTask;

import java.util.Arrays;

public class PokemonDetailsActivity extends AppCompatActivity implements AsyncTaskHandler {

    ImageView image, favorite;
    TextView name, types, weight, experience, id, height;
    RecyclerView rvDetailsTypes;

    AppDatabase database;

    // Pokemon info
    String url;
    String pokemonName;
    Pokemon favoritePokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);



        image = findViewById(R.id.pokemonImage);
        favorite = findViewById(R.id.pokemonFavorite);
        name = findViewById(R.id.pokemonName);
        types = findViewById(R.id.pokemonType);
        weight = findViewById(R.id.pokemonWeight);
        height = findViewById(R.id.pokemonHeight);
        experience = findViewById(R.id.pokemonExp);
        id = findViewById(R.id.pokemonID);
        rvDetailsTypes = findViewById(R.id.recyclerTypes);

        url = getIntent().getStringExtra("URL");

        PokemonDetailsAsyncTask pokemonDetailsAsyncTask = new PokemonDetailsAsyncTask();
        pokemonDetailsAsyncTask.handler = this;
        pokemonDetailsAsyncTask.execute(url);

        database = AppDatabase.getDatabase(this);
    }

    @Override
    public void onTaskEnd(Object result) {
        PokemonDetails details = (PokemonDetails) result;
        pokemonName = details.getName();


        Glide.with(this).load(details.getImage()).into(image);
        name.setText(details.getName());
        weight.setText("Peso: " + details.getWeight());
        height.setText("Altura: " + details.getHeight());
        experience.setText("Experiencia: "  + details.getBaseExperience());
        id.setText("ID: " + details.getId());
        types.setText("Tipo: ");

        rvDetailsTypes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvDetailsTypes.setAdapter(new RowTypesAdapter(this, Arrays.asList(details.getTypes())));

        favoritePokemon = database.pokemonDao().findByName(details.getName());

        if (favoritePokemon != null) {
            Glide.with(this).load(R.drawable.favorite).into(favorite);
        }
    }

    public void onClickType(View view) {
            Intent intent = new Intent(this, PokemonTypeActivity.class);
            startActivity(intent);
    }

    public void onClickFavorite(View view) {
        if(favoritePokemon != null) {
            showAlert(this);
        }
        else {
            Pokemon pokemon = new Pokemon(pokemonName, url);
            database.pokemonDao().insertAll(pokemon);
            Glide.with(this).load(R.drawable.favorite).into(favorite);
        }
    }

    private void showAlert(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("¿Seguro que quieres eliminar este pokemón de favoritos?");

        // Add the buttons
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                database.pokemonDao().delete(favoritePokemon);
                favoritePokemon = null;
                Glide.with(context).load(R.drawable.favorite_empty).into(favorite);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        // Create the AlertDialog
        AlertDialog dialog = builder.create();

        // Show
        dialog.show();
    }
}
