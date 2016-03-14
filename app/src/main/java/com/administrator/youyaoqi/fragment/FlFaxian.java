package com.administrator.youyaoqi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.pagerAdapter.FaxianPagerAdapter;
import com.administrator.youyaoqi.pagerAdapter.ManhuaPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class FlFaxian extends Fragment implements RadioGroup.OnCheckedChangeListener
                                            ,ViewPager.OnPageChangeListener{

    private ViewPager vp_faxian;
    private RadioGroup rg_faxian_top;
    private RadioButton rb_faxian_zhuanti;
    private RadioButton rb_faxian_youxi;
    private ImageView iv_faxian_top_yuan;
    private List<View> viewList;
    private FaxianPagerAdapter fpa;
    private float now;
    private int nowIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fl_main_faxian,null);

        //初始化视图
        vp_faxian = (ViewPager)view.findViewById(R.id.vp_faxian);
        rg_faxian_top = (RadioGroup)view.findViewById(R.id.rg_faxian_top);
        rb_faxian_zhuanti = (RadioButton)view.findViewById(R.id.rb_faxian_zhuanti);
        rb_faxian_youxi = (RadioButton)view.findViewById(R.id.rb_faxian_youxi);
        iv_faxian_top_yuan = (ImageView)view.findViewById(R.id.iv_faxian_top_yuan);

        //设置View数据源
        viewList = new ArrayList<View>();
        viewList.add(inflater.inflate(R.layout.vp_faxian_zhuanti,null));
        viewList.add(inflater.inflate(R.layout.vp_faxian_youxi,null));

        //设置适配器
        fpa = new FaxianPagerAdapter(viewList);
        vp_faxian.setAdapter(fpa);

        //设置监听事件
        rg_faxian_top.setOnCheckedChangeListener(this);
        vp_faxian.addOnPageChangeListener(this);

        //默认选项
        vp_faxian.setCurrentItem(0);
        rb_faxian_zhuanti.setChecked(true);
        now = iv_faxian_top_yuan.getPivotX();

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        setAllTextSize();
        switch (checkedId){
            case R.id.rb_faxian_zhuanti:
                rb_faxian_zhuanti.setTextSize(20);
                vp_faxian.setCurrentItem(0);
                break;
            case R.id.rb_faxian_youxi:
                rb_faxian_youxi.setTextSize(20);
                vp_faxian.setCurrentItem(1);
                break;
        }
    }

    private void setAllTextSize() {
        rb_faxian_zhuanti.setTextSize(17);
        rb_faxian_youxi.setTextSize(17);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation = null;
        switch (position){
            case 0:
                toMove(nowIndex,1,animation);
                rb_faxian_zhuanti.setChecked(true);
                nowIndex = 1;
                break;
            case 1:
                toMove(nowIndex,2,animation);
                rb_faxian_youxi.setChecked(true);
                nowIndex = 2;
                break;
        }
    }

    private void toMove(int nowIndex, int to, Animation animation) {
        float toOne = rg_faxian_top.getWidth()/2;
        switch (to){
            case 1:
                animation = new TranslateAnimation(now+toOne*(nowIndex-1),now,0,0);
                break;
            case 2:
                animation = new TranslateAnimation(now+toOne*(nowIndex-1),now+toOne,0,0);
                break;
        }
        animation.setDuration(100);
        animation.setFillAfter(true);
        iv_faxian_top_yuan.startAnimation(animation);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
