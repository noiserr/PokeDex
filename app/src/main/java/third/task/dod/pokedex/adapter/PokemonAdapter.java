package third.task.dod.pokedex.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import third.task.dod.pokedex.R;
import third.task.dod.pokedex.entity.Pokemon;

/**
 * Created by noiser on 15.07.15.
 */
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.CouchViewHolder> {

    List<Pokemon> pokemonList;

    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @Override
    public CouchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_cardview, parent, false);
        return new CouchViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(CouchViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.pokemonName.setText(pokemon.getName());
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
    public class CouchViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.pokemon_tv_name)
        TextView pokemonName;

        public CouchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
