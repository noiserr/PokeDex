package third.task.dod.pokedex.event;

/**
 * Created by noiser on 15.07.15.
 */
public class ShowDetailsEvent {

    public long couchID;

    public ShowDetailsEvent(long couchID) {
        this.couchID = couchID;
    }
}
