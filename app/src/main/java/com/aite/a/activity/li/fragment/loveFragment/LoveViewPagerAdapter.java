package com.aite.a.activity.li.fragment.loveFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * @Auther: valy
 * @datetime: 2020-01-13
 * @desc:
 */
public class LoveViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    LoveViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
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
}
