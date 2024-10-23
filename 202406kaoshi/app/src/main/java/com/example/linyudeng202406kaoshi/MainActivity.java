package com.example.linyudeng202406kaoshi;

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

import com.example.a202406kaoshi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> myListFragment;//用于存放多个Fragment页
    private List<String> myListString;//用于存放每个Fragment页的标题
    private ViewPager2 myViewPager;
    private TabLayout myTabLayout;
    private MyFragmentStateAdapter myFragmentStateAdapter;
    private FloatingActionButton fab;   //浮动按钮
    private Toolbar toolbar;    //拖把
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
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
        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        myListFragment = new ArrayList<>();
        myListFragment.add(new FirstFragment());
        myListFragment.add(new SecondFragment());


        myListString = new ArrayList<>();
        myListString.add("服务处理");
        myListString.add("异步处理");


        myFragmentStateAdapter = new MyFragmentStateAdapter(this, myListFragment);//适配器初始化
        myViewPager.setAdapter(myFragmentStateAdapter);//适配器设置
        new TabLayoutMediator(myTabLayout, myViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setIcon(imageID[position]);
                tab.setText(myListString.get(position));

            }
        }).attach();//TabLayout与ViewPager2绑定

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "是否确定退出", Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int num = item.getItemId();
                if (num == R.id.nav_SQLlite) myViewPager.setCurrentItem(0);
                if (num == R.id.nav_Startservice) myViewPager.setCurrentItem(1);
//                if (num == R.id.nav_BindService) myViewPager.setCurrentItem(1);
//                if (num == R.id.nav_PageDown) myViewPager.setCurrentItem(1);

                drawer.closeDrawer(GravityCompat.START);
                //drawer.closeDrawers();
                return false;
            }
        });

    }
}