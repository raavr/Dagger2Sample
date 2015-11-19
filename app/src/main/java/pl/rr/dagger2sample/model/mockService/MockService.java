package pl.rr.dagger2sample.model.mockService;

import java.util.Arrays;
import java.util.List;

import pl.rr.dagger2sample.model.Repository;
import pl.rr.dagger2sample.model.api.GithubApi;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Rafal on 2015-06-18.
 */
public class MockService implements GithubApi {

    @Override
    public Observable<List<Repository>> getRepository(String user) {
        return Observable.just(Arrays.asList(createRepository(1, "Repository 1"),
                createRepository(2, "Repository 2"),
                createRepository(3, "Repository 3")));
    }

    private Repository createRepository(int id, String name) {
        return new Repository(id, name);
    }
}
