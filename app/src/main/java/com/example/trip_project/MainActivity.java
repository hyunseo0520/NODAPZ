package com.example.trip_project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity {
    Fragment fragment1, fragment2, fragment3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,fragment1).commit();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                switch (position){
                    case 0:selected=fragment1; break;
                    case 1:selected=fragment2; break;
                    case 2:selected=fragment3; break;
                    default: ;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        TabLayout.Tab defaultTab = tabLayout.getTabAt(1);
        if (defaultTab != null) {
            defaultTab.select();
        }
    }
}