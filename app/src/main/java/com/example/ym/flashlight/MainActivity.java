package com.example.ym.flashlight;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private FragAdapter fragAdapter;
    private List<Fragment> fragments;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setSupportActionBar(toolbar);
        initViewPager();



    }

    private void initViewPager(){
        tabLayout.setupWithViewPager(viewPager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new FlashFrag());
        fragments.add(new OtherFrag());
        fragAdapter = new FragAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(fragAdapter);


    }







}
