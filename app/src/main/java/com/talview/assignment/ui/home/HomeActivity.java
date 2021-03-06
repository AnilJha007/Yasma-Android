package com.talview.assignment.ui.home;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.talview.assignment.R;
import com.talview.assignment.application.MyApp;
import com.talview.assignment.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private int ITEM_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setUpToolbar();

        setUpViewPagerAndTabLayout();

    }

    private void setUpViewPagerAndTabLayout() {

        TabLayout tabLayout = binding.tablayoutHome;
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.posts)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.albums)));

        binding.viewpagerHome.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager(), ITEM_COUNT));

        binding.viewpagerHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewpagerHome.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpToolbar() {

        setSupportActionBar(binding.toolbarHome);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }
}
