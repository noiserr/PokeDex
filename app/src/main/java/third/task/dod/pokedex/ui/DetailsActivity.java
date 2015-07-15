package third.task.dod.pokedex.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import third.task.dod.pokedex.DatabaseManager;
import third.task.dod.pokedex.cons.Const;
import third.task.dod.pokedex.R;
import third.task.dod.pokedex.adapter.PokemonAdapter;
import third.task.dod.pokedex.entity.CouchPokemons;
import third.task.dod.pokedex.entity.Pokemon;

public class DetailsActivity extends AppCompatActivity {


    @Bind(R.id.detail_activity_toolbar)
    Toolbar toolbar;
    @Bind(R.id.detail_activity_pokemon_rv)
    RecyclerView recyclerView;

    PokemonAdapter pokemonAdapter;
    long couchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();
        Intent intent = getIntent();
        couchId = intent.getExtras().getLong(Const.COUCH_ID);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        new PokemonAsyncTask().execute();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addPokemon(View view) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Diglet");
        DatabaseManager.getInstance().savePokemon(pokemon);
        pokemonAdapter.addPokemon(pokemon);
        pokemonAdapter.notifyDataSetChanged();
    }

    private class PokemonAsyncTask extends AsyncTask<Void, Void, List<Pokemon>> {
        @Override
        protected List<Pokemon> doInBackground(Void... params) {
            List<Pokemon> pokemons = new ArrayList<>();
            List<CouchPokemons> couchPokemonsList = new Select().from(CouchPokemons.class).where("couch = ?", couchId).execute();
            for (CouchPokemons couchPokemons : couchPokemonsList) {
                pokemons.add(couchPokemons.getPokemon());
            }
            return pokemons;
        }

        @Override
        protected void onPostExecute(List<Pokemon> pokemons) {
            super.onPostExecute(pokemons);
            pokemonAdapter = new PokemonAdapter(pokemons);
            recyclerView.setAdapter(pokemonAdapter);
        }
    }
}
