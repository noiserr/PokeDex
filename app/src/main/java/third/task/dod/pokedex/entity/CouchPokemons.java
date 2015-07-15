package third.task.dod.pokedex.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by noiser on 15.07.15.
 */
@Table(name = "couch_pokemon")
public class CouchPokemons extends Model {

    @Column(onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private Couch couch;
    @Column(onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private Pokemon pokemon;

    public Couch getCouch() {
        return couch;
    }

    public void setCouch(Couch couch) {
        this.couch = couch;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
