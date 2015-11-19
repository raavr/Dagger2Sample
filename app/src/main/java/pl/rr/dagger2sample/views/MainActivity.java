package pl.rr.dagger2sample.views;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.rr.dagger2sample.Dagger2Application;
import pl.rr.dagger2sample.R;
import pl.rr.dagger2sample.dagger.component.DaggerActivityComponent;
import pl.rr.dagger2sample.dagger.module.ActivityModule;
import pl.rr.dagger2sample.mvp.presenter.MainPresenter;
import pl.rr.dagger2sample.mvp.view.MainView;


public class MainActivity extends AppCompatActivity implements MainView {

    public static final String EXTRA_USER_NAME = "extra_user_name";

    @Inject
    MainPresenter mainPresenter;

    @InjectView(R.id.github_user_name)
    EditText githubUserNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this, this);
        initializeInjector();
        initializePresenter();
    }

    private void initializeInjector() {

        Dagger2Application application = (Dagger2Application) getApplication();

        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComonent(application.getAppComonent())
                .build().inject(this);
    }

    private void initializePresenter() {
        mainPresenter.attachView(this);
    }


    @OnClick(R.id.main_btn)
    public void onReposBtnClick() {
        String userName = githubUserNameTv.getText().toString();
        if(!userName.equals(""))
            mainPresenter.onClick(userName);
        else
            showError("You should add user name");
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
