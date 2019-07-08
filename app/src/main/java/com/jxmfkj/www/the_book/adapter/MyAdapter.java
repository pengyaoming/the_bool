package com.jxmfkj.www.the_book.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxmfkj.www.the_book.R;

import java.util.ArrayList;

public class MyAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> Icons;
    private Context mContext;

    public MyAdapter(FragmentManager fm, ArrayList<String> title, ArrayList<Fragment> fragments, ArrayList<Integer> Icons, Context mContext) {
        super(fm);
        this.titles = title;
        this.fragments = fragments;
        this.Icons = Icons;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public View getTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.view_main, null);
        TextView tv = (TextView) v.findViewById(R.id.tv_tab);
        tv.setText(titles.get(position));
        ImageView img = (ImageView) v.findViewById(R.id.icon_image);
        img.setImageResource(Icons.get(position));
        return v;
    }
}
