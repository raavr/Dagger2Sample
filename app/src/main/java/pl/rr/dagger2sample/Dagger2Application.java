package pl.rr.dagger2sample;

import android.app.Application;

import pl.rr.dagger2sample.dagger.component.AppComonent;
import pl.rr.dagger2sample.dagger.component.DaggerAppComonent;
import pl.rr.dagger2sample.dagger.module.AppModule;

/**
 * Created by Rafal on 2015-06-17.
 */
public class Dagger2Application extends Application {

    private AppComonent appComonent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        appComonent = DaggerAppComonent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComonent getAppComonent() {
        return appComonent;
    }

    public void setComponent(AppComonent component) {
        this.appComonent = component;
    }

}
