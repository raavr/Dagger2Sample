package pl.rr.dagger2sample.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.rr.dagger2sample.R;
import pl.rr.dagger2sample.model.Repository;

/**
 * Created by Rafal on 2015-06-18.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {


    private Context mContext;
    private List<Repository> repositoryList;

    public RecyclerAdapter(Context context, List<Repository> repositories) {
        this.mContext = context;
        this.repositoryList = repositories;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.repo_item, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int i) {

        recyclerViewHolder.repoName.setText(repositoryList.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public void addRepo(Repository repo) {
        repositoryList.add(repo);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.list_item)
        TextView repoName;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
