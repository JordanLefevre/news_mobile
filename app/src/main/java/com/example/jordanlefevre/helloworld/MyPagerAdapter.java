package com.example.jordanlefevre.helloworld;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jordanlefevre on 15/03/2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    public Category[] categories;

    public MyPagerAdapter(FragmentManager fm, Category[] categories) {
        super(fm);
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Fragment getItem(int position) {
        return ListFragment.newInstance(categories[position].id);
    }

    @Override
    public String getPageTitle(int position) {
        return categories[position].title;
    }
}