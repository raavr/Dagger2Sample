package pl.rr.dagger2sample.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.rr.dagger2sample.Dagger2Application;
import pl.rr.dagger2sample.R;
import pl.rr.dagger2sample.dagger.component.DaggerActivityComponent;
import pl.rr.dagger2sample.dagger.module.ActivityModule;
import pl.rr.dagger2sample.model.Repository;
import pl.rr.dagger2sample.mvp.presenter.RepositoryPresenter;
import pl.rr.dagger2sample.mvp.view.RepositoryView;
import pl.rr.dagger2sample.views.adapter.RecyclerAdapter;

public class RepositoryActivity extends AppCompatActivity implements RepositoryView {

    @Inject
    RepositoryPresenter presenter;

    @InjectView(R.id.recycler_viewer)
    RecyclerView mRecyclerView;

    @InjectView(R.id.progress_bar)
    ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.inject(this, this);
        initializeInjector();
        initializePresenter();
        initializeRecyclerViewer();
    }

    private void initializeInjector() {
        Dagger2Application application = (Dagger2Application) getApplication();

        DaggerActivityComponent.builder()
                .appComonent(application.getAppComonent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);

    }

    public void initializePresenter() {
        presenter.attachView(this);
        presenter.attachIncominigIntent(getIntent());
        presenter.initializePresenter();
    }

    private void initializeRecyclerViewer() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        RecyclerAdapter adapter = new RecyclerAdapter(this, new ArrayList<>());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress(boolean isLoading) {
        mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }

    @Override
    public void addRepos(Repository repo) {
        ((RecyclerAdapter) mRecyclerView.getAdapter()).addRepo(repo);
    }

    @Override
    public void refreshAdapter() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }


}
