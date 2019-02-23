package com.talview.assignment.ui.home;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.talview.assignment.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    private HomeActivity homeActivity;


    @Before
    public void setUp() throws Exception {
        homeActivity = activityTestRule.getActivity();
    }

    @Test
    public void launchHomeActivityTest() {

        View view = homeActivity.findViewById(R.id.viewpager_home);
        assertNotNull(view);
    }

    @Test
    public void swipeViewPager() {
        onView(withId(R.id.viewpager_home))
                .check(matches(isDisplayed()));

        onView(withId(R.id.viewpager_home))
                .perform(swipeLeft());
    }

    @Test
    public void checkTabLayoutDisplayed() {
        onView(withId(R.id.tablayout_home))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkNextActivityLaunch() {

        if (getRowViewCount() > 0) {
            onView(withId(R.id.recycler_posts)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
    }

    @After
    public void tearDown() throws Exception {
        homeActivity = null;
    }

    private int getRowViewCount() {
        RecyclerView recyclerView = homeActivity.findViewById(R.id.recycler_posts);
        return recyclerView.getAdapter().getItemCount();
    }
}