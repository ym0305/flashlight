package com.example.ym.flashlight;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YM on 2017/3/8.
 */

public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles = new String[]{"闪光灯","其他"};

    public FragAdapter(FragmentManager fm , List<Fragment> fragments){
        super(fm);
        this.fragments = fragments;

    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public CharSequence getPageTitle(int position){
        return titles[position];
    }
}
