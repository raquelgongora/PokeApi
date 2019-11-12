package org.sara.pokedex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.sara.pokedex.R;
import org.sara.pokedex.entities.PokemonDetails;
import org.sara.pokedex.interfaces.AsyncTaskHandler;
import org.sara.pokedex.network.PokemonDetailsAsyncTask;

public class PokemonDetailsActivity extends AppCompatActivity implements AsyncTaskHandler {

    ImageView image, favorite;
    TextView name, types, weight, experience, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        image = findViewById(R.id.details_image);
        favorite = findViewById(R.id.details_favorite);
        name = findViewById(R.id.detatils_name);
        types = findViewById(R.id.detatils_type);
        weight = findViewById(R.id.detatils_weight);
        experience = findViewById(R.id.detatils_experience);
        id = findViewById(R.id.detatils_id);

        String url = getIntent().getStringExtra("URL");

        PokemonDetailsAsyncTask pokemonDetailsAsyncTask = new PokemonDetailsAsyncTask();
        pokemonDetailsAsyncTask.handler = this;
        pokemonDetailsAsyncTask.execute(url);
    }

    @Override
    public void onTaskEnd(Object result) {
        PokemonDetails details = (PokemonDetails) result;

        Glide.with(this).load(details.getImage()).into(image);
        name.setText(details.getName());
        weight.setText("Peso: " + details.getWeight());
        experience.setText("Experiencia: "  + details.getBaseExperience());
        id.setText("ID: " + details.getId());

        String typesString = "";

        for (int i = 0; i < types.length(); i++) {
            typesString += details.getType();
        }

        types.setText("Tipo: " + typesString);
    }
}
