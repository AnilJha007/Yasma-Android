package com.talview.assignment.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    private int ITEM_COUNT;

    public HomeViewPagerAdapter(FragmentManager fm, int itemCount) {
        super(fm);

        this.ITEM_COUNT = itemCount;
    }

    @Override
    public Fragment getItem(int fragPosition) {

        switch (fragPosition) {
            case 0:
                return new PostsFragment();

            case 1:
                return new AlbumsFragment();

            default:
                return new PostsFragment();
        }

    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }
}
