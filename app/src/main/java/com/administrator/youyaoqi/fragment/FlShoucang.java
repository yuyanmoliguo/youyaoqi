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
import com.administrator.youyaoqi.pagerAdapter.ShoucangPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class FlShoucang extends Fragment implements RadioGroup.OnCheckedChangeListener
                                             ,ViewPager.OnPageChangeListener{

    private ViewPager vp_shoucang;
    private RadioGroup rg_shoucang_top;
    private RadioButton rb_shoucang_shoucang;
    private RadioButton rb_shoucang_lishi;
    private RadioButton rb_shoucang_bendi;
    private ImageView iv_shoucang_top_yuan;
    private List<View> viewList;
    private ShoucangPagerAdapter spa;
    private ImageView ib_shoucang_null;
    private ImageView ib_shoucang_f5;
    private ImageView ib_shoucang_del;
    private ImageView ib_shoucang_add;
    private float now;
    private int nowIndex=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fl_main_shoucang,null);

        //初始化视图
        vp_shoucang = (ViewPager)view.findViewById(R.id.vp_shoucang);
        rg_shoucang_top = (RadioGroup)view.findViewById(R.id.rg_shoucang_top);
        rb_shoucang_shoucang = (RadioButton)view.findViewById(R.id.rb_shoucang_shoucang);
        rb_shoucang_lishi = (RadioButton)view.findViewById(R.id.rb_shoucang_lishi);
        rb_shoucang_bendi = (RadioButton)view.findViewById(R.id.rb_shoucang_bendi);
        iv_shoucang_top_yuan = (ImageView)view.findViewById(R.id.iv_shoucang_top_yuan);
        ib_shoucang_null = (ImageView)view.findViewById(R.id.ib_shoucang_null);
        ib_shoucang_del = (ImageView)view.findViewById(R.id.ib_shoucang_del);
        ib_shoucang_f5 = (ImageView)view.findViewById(R.id.ib_shoucang_f5);
        ib_shoucang_add = (ImageView)view.findViewById(R.id.ib_shoucang_add);


        //设置View数据源
        viewList = new ArrayList<View>();
        viewList.add(inflater.inflate(R.layout.vp_shoucang_shoucang,null));
        viewList.add(inflater.inflate(R.layout.vp_shoucang_lishi,null));
        viewList.add(inflater.inflate(R.layout.vp_shoucang_bendi,null));

        //设置适配器
        spa = new ShoucangPagerAdapter(viewList);
        vp_shoucang.setAdapter(spa);

        //设置监听事件
        vp_shoucang.addOnPageChangeListener(this);
        rg_shoucang_top.setOnCheckedChangeListener(this);

        //默认选项
        vp_shoucang.setCurrentItem(0);
        rb_shoucang_shoucang.setChecked(true);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        setAllTextSize();
        setAllIbGone();
        switch (checkedId){
            case R.id.rb_shoucang_shoucang:
                ib_shoucang_null.setVisibility(View.VISIBLE);
                ib_shoucang_f5.setVisibility(View.VISIBLE);
                rb_shoucang_shoucang.setTextSize(20);
                vp_shoucang.setCurrentItem(0);
                break;
            case R.id.rb_shoucang_lishi:
                ib_shoucang_del.setVisibility(View.VISIBLE);
                ib_shoucang_f5.setVisibility(View.VISIBLE);
                rb_shoucang_lishi.setTextSize(20);
                vp_shoucang.setCurrentItem(1);
                break;
            case R.id.rb_shoucang_bendi:
                ib_shoucang_null.setVisibility(View.VISIBLE);
                ib_shoucang_add.setVisibility(View.VISIBLE);
                rb_shoucang_bendi.setTextSize(20);
                vp_shoucang.setCurrentItem(2);
                break;
        }
    }

    private void setAllIbGone() {
        ib_shoucang_add.setVisibility(View.GONE);
        ib_shoucang_del.setVisibility(View.GONE);
        ib_shoucang_null.setVisibility(View.GONE);
        ib_shoucang_f5.setVisibility(View.GONE);
    }

    private void setAllTextSize() {
        rb_shoucang_shoucang.setTextSize(17);
        rb_shoucang_lishi.setTextSize(17);
        rb_shoucang_bendi.setTextSize(17);
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
                rb_shoucang_shoucang.setChecked(true);
                nowIndex = 1;
                break;
            case 1:
                toMove(nowIndex,2,animation);
                rb_shoucang_lishi.setChecked(true);
                nowIndex = 2;
                break;
            case 2:
                toMove(nowIndex,3,animation);
                rb_shoucang_bendi.setChecked(true);
                nowIndex = 3;
                break;
        }
    }

    private void toMove(int nowIndex, int to, Animation animation) {
        float toOne = rg_shoucang_top.getWidth()/5;
        switch (to){
            case 1:
                animation = new TranslateAnimation(now+toOne*(nowIndex-1),now,0,0);
                break;
            case 2:
                animation = new TranslateAnimation(now+toOne*(nowIndex-1),now+toOne,0,0);
                break;
            case 3:
                animation = new TranslateAnimation(now+toOne*(nowIndex-1),now+2*toOne,0,0);
                break;
        }
        animation.setDuration(100);
        animation.setFillAfter(true);
        iv_shoucang_top_yuan.startAnimation(animation);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
