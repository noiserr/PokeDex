package third.task.dod.pokedex.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "pokemon")
public class Pokemon extends Model {

    @Column
    private String name;

    public Pokemon() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
