package com.talview.assignment.ui.home;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.talview.assignment.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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

    @After
    public void tearDown() throws Exception {
        homeActivity = null;
    }
}