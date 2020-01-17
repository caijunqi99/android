package com.example.green.adapter.store;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> pList) {
        super(fm);
        mList = pList;
    }

    @Override
    public Fragment getItem(int pI) {
        return mList.get(pI);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}