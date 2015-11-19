package pl.rr.dagger2sample.dagger.component;

import android.content.Context;

import dagger.Component;
import pl.rr.dagger2sample.dagger.ForActivity;
import pl.rr.dagger2sample.dagger.module.ActivityModule;
import pl.rr.dagger2sample.model.Repository;
import pl.rr.dagger2sample.views.MainActivity;
import pl.rr.dagger2sample.views.RepositoryActivity;

/**
 * Created by Rafal on 2015-06-18.
 */

@ForActivity
@Component(dependencies = AppComonent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(RepositoryActivity repositoryActivity);
    Context provideContext();

}
