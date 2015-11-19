package pl.rr.dagger2sample.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.rr.dagger2sample.Dagger2Application;
import pl.rr.dagger2sample.model.api.GithubApi;
import pl.rr.dagger2sample.model.mockService.MockService;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Rafal on 2015-06-18.
 */
@Module
public class AppModule {

    private static final String ENDPOINT = "https://api.github.com/";
    private final Dagger2Application dagger2Application;

    public AppModule(Dagger2Application dagger2Application) {
        this.dagger2Application = dagger2Application;
    }

    @Provides
    @Singleton
    Dagger2Application provideApplication() {
        return dagger2Application;
    }

    @Provides
    @Singleton
    GithubApi provideService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        return restAdapter.create(GithubApi.class);
    }

//    @Provides
//    @Singleton
//    GithubApi provideMockService() {
//        return new MockService();
//    }

}
