package org.dudar.roman.yalantis2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Roman on 22.04.2016.
 */
class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mPageTitles;

    public ViewPagerAdapter(FragmentManager manager, String[] pageTitles) {
        super(manager);
        mPageTitles = pageTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return TicketsFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mPageTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitles[position];
    }
}