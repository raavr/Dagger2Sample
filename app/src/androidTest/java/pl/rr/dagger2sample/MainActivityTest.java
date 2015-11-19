package pl.rr.dagger2sample;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static pl.rr.dagger2sample.ViewMatchers.onRecyclerItemView;

/**
 * Created by Rafal on 2015-06-18.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String REPOSITORY_TEXT = "Repository 2";
    private static final String USER_NAME = "some user";
    private static final String ERROR_TOAST_MSG = "You should add user name";


    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
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
    public void shouldRunRepositoryActivityAndLoadMockData() {

        activityRule.launchActivity(new Intent());
        onView(withId(R.id.github_user_name)).perform(typeText(USER_NAME), closeSoftKeyboard());
        onView(withId(R.id.main_btn)).perform(click());

        onRecyclerItemView(R.id.list_item, withText(REPOSITORY_TEXT), withId(R.id.list_item))
                .check(matches(withText(REPOSITORY_TEXT)));

    }

    @Test
    public void shouldChangeText() {

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.github_user_name)).perform(typeText(USER_NAME), closeSoftKeyboard());
        onView(withId(R.id.github_user_name)).check(matches(withText(USER_NAME)));
    }


    @Test
    public void shouldShowToast() {

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.main_btn)).perform(click());
        onView(withText(ERROR_TOAST_MSG))
                .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
}
