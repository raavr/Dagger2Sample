package pl.rr.dagger2sample.dagger;

import javax.inject.Singleton;

import dagger.Component;
import pl.rr.dagger2sample.Dagger2Application;
import pl.rr.dagger2sample.dagger.component.AppComonent;
import pl.rr.dagger2sample.model.api.GithubApi;

/**
 * Created by Rafal on 2015-06-18.
 */
@Singleton
@Component(modules = MockModule.class)
public interface MockComponent extends AppComonent {

    Dagger2Application provideApplication();
    GithubApi provideService();
}
