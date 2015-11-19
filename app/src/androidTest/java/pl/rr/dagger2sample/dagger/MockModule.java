package pl.rr.dagger2sample.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.rr.dagger2sample.Dagger2Application;
import pl.rr.dagger2sample.model.api.GithubApi;
import pl.rr.dagger2sample.model.mockService.MockService;

/**
 * Created by Rafal on 2015-06-18.
 */
@Module
public class MockModule {

    private final Dagger2Application dagger2Application;

    public MockModule(Dagger2Application dagger2Application) {
        this.dagger2Application = dagger2Application;
    }

    @Provides
    @Singleton
    GithubApi provideMockService() {
        return new MockService();
    }

    @Provides
    @Singleton
    Dagger2Application provideApplication() {
        return dagger2Application;
    }


}
