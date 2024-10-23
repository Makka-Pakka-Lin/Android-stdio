package com.example.linyudeng202406kaoshi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyFragmentStateAdapter extends FragmentStateAdapter {
    List<Fragment> myFragmentList;

    public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> myFragmentList) {
        super(fragmentActivity);
        this.myFragmentList = myFragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return myFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return myFragmentList.size();
    }
}
