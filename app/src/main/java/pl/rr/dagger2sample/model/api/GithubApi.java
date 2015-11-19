package pl.rr.dagger2sample.model.api;

import java.util.List;

import pl.rr.dagger2sample.model.Repository;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Rafal on 2015-06-17.
 */
public interface GithubApi {

    @GET("/users/{user}/repos")
    @Headers("User-Agent: Dagger2Sample")
    Observable<List<Repository>> getRepository(@Path("user") String user);
}
