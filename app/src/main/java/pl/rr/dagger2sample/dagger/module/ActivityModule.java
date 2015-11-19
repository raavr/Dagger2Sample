package pl.rr.dagger2sample.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.rr.dagger2sample.dagger.ForActivity;

/**
 * Created by Rafal on 2015-06-18.
 */
@Module
public class ActivityModule {

    private final Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides @ForActivity
    Context provideContext() {
        return context;
    }
}
