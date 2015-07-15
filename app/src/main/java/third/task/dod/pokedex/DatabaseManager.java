package third.task.dod.pokedex;


import third.task.dod.pokedex.entity.Couch;
import third.task.dod.pokedex.entity.CouchPokemons;
import third.task.dod.pokedex.entity.Pokemon;

public class DatabaseManager {

    private static DatabaseManager databaseManager;

    private DatabaseManager() {

    }

    public static DatabaseManager getInstance(){
        if(databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public boolean savePokemon(Pokemon pokemon) {
        return pokemon.save() > -1;
    }

    public boolean saveCouch(Couch couch) {
        return couch.save() > -1;
    }

    public boolean saveUnion(CouchPokemons couchPokemons) {
        return couchPokemons.save() > -1;
    }
}
