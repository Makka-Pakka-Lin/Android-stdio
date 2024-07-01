package com.example.a202406kaoshi;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> myListFragment;
    private List<String> myListString;
    private ViewPager2 myViewPager;
    private TabLayout myTabLayout;
    private MyFragmentStateAdapter myFragmentStateAdapter;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private final int[] imageID = {R.drawable.one, R.drawable.two};

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewPager = findViewById(R.id.vp_pager);
        myTabLayout = findViewById(R.id.tab_title);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        myListFragment = new ArrayList<>();
        myListFragment.add(new FirstFragment());
        myListFragment.add(new SecondFragment());


        myListString = new ArrayList<>();
        myListString.add("服务处理");
        myListString.add("异步处理");


        myFragmentStateAdapter = new MyFragmentStateAdapter(this, myListFragment);
        myViewPager.setAdapter(myFragmentStateAdapter);
        new TabLayoutMediator(myTabLayout, myViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setIcon(imageID[position]);
                tab.setText(myListString.get(position));

            }
        }).attach();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setItemIconTintList(null);


    }
}