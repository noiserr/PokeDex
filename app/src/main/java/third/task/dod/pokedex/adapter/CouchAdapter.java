package third.task.dod.pokedex.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import third.task.dod.pokedex.R;
import third.task.dod.pokedex.entity.Couch;
import third.task.dod.pokedex.event.ShowDetailsEvent;

/**
 * Created by noiser on 15.07.15.
 */
public class CouchAdapter extends RecyclerView.Adapter<CouchAdapter.CouchViewHolder> {

    List<Couch> couchList;

    public CouchAdapter(List<Couch> couchList) {
        this.couchList = couchList;
    }

    public void addCouch(Couch couch) {
        couchList.add(couch);
    }

    @Override
    public CouchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.couch_cardview, parent, false);
        return new CouchViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(CouchViewHolder holder, int position) {
        Couch couch = couchList.get(position);
        holder.couchName.setText(couch.getName());
    }

    @Override
    public int getItemCount() {
        return couchList.size();
    }

    public class CouchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.couch_tv_name)
        TextView couchName;

        public CouchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new ShowDetailsEvent(couchList.get(getAdapterPosition()).getId()));
            Log.wtf("BOOTCAMP", "CLICk!");
        }
    }
}
