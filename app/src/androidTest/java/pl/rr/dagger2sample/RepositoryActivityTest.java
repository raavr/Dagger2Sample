package pl.rr.dagger2sample;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.rr.dagger2sample.dagger.DaggerMockComponent;
import pl.rr.dagger2sample.dagger.MockComponent;
import pl.rr.dagger2sample.dagger.MockModule;
import pl.rr.dagger2sample.views.MainActivity;
import pl.rr.dagger2sample.views.RepositoryActivity;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static pl.rr.dagger2sample.ViewMatchers.onRecyclerItemView;


/**
 * Created by Rafal on 2015-06-18.
 */

@RunWith(AndroidJUnit4.class)
public class RepositoryActivityTest {

    private static final String REPOSITORY_TEXT = "Repository 1";

    @Rule
    public ActivityTestRule<RepositoryActivity> activityRule = new ActivityTestRule<>(
            RepositoryActivity.class,
            true,
            false);

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Dagger2Application app
                = (Dagger2Application) instrumentation.getTargetContext().getApplicationContext();

            MockComponent component = DaggerMockComponent.builder()
                    .mockModule(new MockModule(app))
                    .build();

        app.setComponent(component);

    }

    @Test
    public void shouldLoadMockData() {

        Intent intent = new Intent().putExtra(MainActivity.EXTRA_USER_NAME, "some user");
        activityRule.launchActivity(intent);

        onRecyclerItemView(R.id.list_item, withText(REPOSITORY_TEXT), withId(R.id.list_item))
                .check(matches(withText(REPOSITORY_TEXT)));

    }


}
