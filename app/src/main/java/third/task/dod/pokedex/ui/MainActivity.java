package third.task.dod.pokedex.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.activeandroid.query.Select;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import third.task.dod.pokedex.Const;
import third.task.dod.pokedex.DatabaseManager;
import third.task.dod.pokedex.R;
import third.task.dod.pokedex.adapter.CouchAdapter;
import third.task.dod.pokedex.entity.Couch;
import third.task.dod.pokedex.entity.CouchPokemons;
import third.task.dod.pokedex.entity.Pokemon;
import third.task.dod.pokedex.event.ShowDetailsEvent;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_activity_toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_activity_pokemon_rv)
    RecyclerView recyclerView;
    CouchAdapter couchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpEventBus();
    }

    private void setUpEventBus() {
        EventBus.getDefault().register(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }


    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        new CouchAsyncTask().execute();

    }

    public void onEvent(ShowDetailsEvent event){
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Const.COUCH_ID, event.couchID);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private class CouchAsyncTask extends AsyncTask<Void, Void, List<Couch>>{

        @Override
        protected List<Couch> doInBackground(Void... params) {
            return Couch.getAllCouches();
        }
        @Override
        protected void onPostExecute(List<Couch> couches) {
            super.onPostExecute(couches);
            couchAdapter = new CouchAdapter(couches);
            recyclerView.setAdapter(couchAdapter);
        }

    }
}
