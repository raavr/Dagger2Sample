package pl.rr.dagger2sample.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import pl.rr.dagger2sample.model.api.GithubApi;
import pl.rr.dagger2sample.mvp.view.MainView;
import pl.rr.dagger2sample.mvp.view.View;
import pl.rr.dagger2sample.views.MainActivity;
import pl.rr.dagger2sample.views.RepositoryActivity;

/**
 * Created by Rafal on 2015-06-18.
 */
public class MainPresenter implements Presenter {

    private Context mContext;
    private MainView mView;

    @Inject
    public MainPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void attachView(View v) {
        mView = (MainView) v;
    }

    @Override
    public void attachIncominigIntent(Intent intent) {
        //empty
    }

    public void onClick(String userName) {
        Intent i = new Intent(mContext, RepositoryActivity.class);
        i.putExtra(MainActivity.EXTRA_USER_NAME, userName);
        mContext.startActivity(i);
    }
}
