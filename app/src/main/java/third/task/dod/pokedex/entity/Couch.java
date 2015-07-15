package third.task.dod.pokedex.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by noiser on 15.07.15.
 */
@Table(name = "couch")
public class Couch extends Model {

    @Column
    private String name;

    public Couch() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static public List<Couch> getAllCouches() {
        return new Select().from(Couch.class).execute();
    }
}
