package com.example.newpc.laboratory.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.newpc.laboratory.R;
import com.example.newpc.laboratory.fragments.FragmentA;
import com.example.newpc.laboratory.fragments.FragmentB;
import com.example.newpc.laboratory.fragments.FragmentC;
import com.example.newpc.laboratory.fragments.FragmentD;

/**
 * Created by Francisco Gabriel on 13/10/2017.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
    private String[] mTabTitles;


    public MyFragmentPagerAdapter(FragmentManager fm, String[] mTabTitles) {
        super(fm);
        this.mTabTitles = mTabTitles;
       // int icon[] = new int []{R.drawable.ativado};
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentA();
            case 1:
                return new FragmentB();
            case 2:
                return new FragmentC();
            case 3:
                return new FragmentD();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
     //   return this.mTabTitles[position];
       return "";
    }

}
