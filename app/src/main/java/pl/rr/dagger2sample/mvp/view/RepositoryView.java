package pl.rr.dagger2sample.mvp.view;

import pl.rr.dagger2sample.model.Repository;

/**
 * Created by Rafal on 2015-06-18.
 */
public interface RepositoryView extends View{

    void showProgress(boolean isLoading);
    void addRepos(Repository repo);
    void refreshAdapter();

}
