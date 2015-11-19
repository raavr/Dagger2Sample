package pl.rr.dagger2sample.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import javax.inject.Inject;

import pl.rr.dagger2sample.model.Repository;
import pl.rr.dagger2sample.model.api.GithubApi;
import pl.rr.dagger2sample.mvp.view.RepositoryView;
import pl.rr.dagger2sample.mvp.view.View;
import pl.rr.dagger2sample.views.MainActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rafal on 2015-06-18.
 */
public class RepositoryPresenter implements Presenter {

    private Context mContext;
    private GithubApi mGithubApi;
    private RepositoryView mView;
    private Intent mIntent;

    @Inject
    public RepositoryPresenter(Context context, GithubApi githubApi) {
        mContext = context;
        mGithubApi = githubApi;
    }

    public void initializePresenter() {
        String userName = mIntent.getStringExtra(MainActivity.EXTRA_USER_NAME);

        mView.showProgress(true);

        mGithubApi.getRepository(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(repos -> Observable.from(repos))
                .subscribe(repos -> onNext(repos), error -> onError(error), () -> onComleted());
    }

    @Override
    public void attachView(View v) {
        mView = (RepositoryView) v;
    }

    @Override
    public void attachIncominigIntent(Intent intent) {
        mIntent = intent;
    }

    private void onNext(Repository repo) {
        mView.addRepos(repo);
    }

    private void onComleted() {
        mView.showProgress(false);
        mView.refreshAdapter();
    }

    private void onError(Throwable throwable) {
        mView.showError("Network error");
    }


}
