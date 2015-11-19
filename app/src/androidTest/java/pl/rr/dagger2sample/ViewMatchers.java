package pl.rr.dagger2sample;

import android.support.annotation.IdRes;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.CoreMatchers.allOf;

//https://gist.github.com/tommyd3mdi/2622caecc1b2d498cd1a
public class ViewMatchers
{
    @SuppressWarnings("unchecked")
    public static Matcher<View> withRecyclerView(@IdRes int viewId)
    {
        return allOf(isAssignableFrom(RecyclerView.class), withId(viewId));
    }

    @SuppressWarnings("unchecked")
    public static ViewInteraction onRecyclerItemView(@IdRes int identifyingView, Matcher<View> identifyingMatcher, Matcher<View> childMatcher)
    {
        Matcher<View> itemView = allOf(withParent(withRecyclerView(R.id.recycler_viewer)),
                withChild(allOf(withId(identifyingView), identifyingMatcher)));
        return onView(allOf(isDescendantOfA(itemView), childMatcher));
    }


}
