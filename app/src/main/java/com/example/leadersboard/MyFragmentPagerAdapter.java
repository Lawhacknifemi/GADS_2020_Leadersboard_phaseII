package com.example.leadersboard;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public MyFragmentPagerAdapter(Context  context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new HoursFragment();
        }else {
            return new SkillFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){

            return mContext.getString(R.string.learning_fragment_text);

        }else {
            return mContext.getString(R.string.skill_fragment_text);
        }
    }
}
