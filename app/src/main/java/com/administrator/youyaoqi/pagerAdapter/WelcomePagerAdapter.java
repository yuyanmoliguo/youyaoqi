package com.administrator.youyaoqi.pagerAdapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
public class WelcomePagerAdapter extends PagerAdapter {
    private List<View> viewList;

    public WelcomePagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList==null?0:viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        if (viewList!=null){
            view = viewList.get(position);
            container.addView(view);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (viewList!=null){
            container.removeView(viewList.get(position));
        }
    }
}
