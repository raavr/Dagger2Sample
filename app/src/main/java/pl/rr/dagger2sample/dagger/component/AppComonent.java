package pl.rr.dagger2sample.dagger.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import pl.rr.dagger2sample.Dagger2Application;
import pl.rr.dagger2sample.dagger.module.AppModule;
import pl.rr.dagger2sample.model.api.GithubApi;

/**
 * Created by Rafal on 2015-06-18.
 */
@Singleton @Component(modules = AppModule.class)
public interface AppComonent {

    Dagger2Application provideApplication();
    GithubApi provideService();
}
