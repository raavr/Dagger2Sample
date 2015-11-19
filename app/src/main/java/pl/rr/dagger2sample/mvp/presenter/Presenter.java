package pl.rr.dagger2sample.mvp.presenter;

import android.content.Intent;

import pl.rr.dagger2sample.mvp.view.View;

/**
 * Created by Rafal on 2015-06-18.
 */
public interface Presenter {

    void attachView(View v);
    void attachIncominigIntent(Intent intent);
}
