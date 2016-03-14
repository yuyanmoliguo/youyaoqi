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
import com.administrator.youyaoqi.pagerAdapter.ManhuaPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class FlManhua extends Fragment implements RadioGroup.OnCheckedChangeListener
                                            ,ViewPager.OnPageChangeListener{

    private RadioGroup rg_manhua_top;
    private RadioButton rb_manhua_jingpin;
    private List<Fragment> fragmentList;
    private ManhuaPagerAdapter mpa;
    private ViewPager vp_manhua;
    private RadioButton rb_manhua_gengxin;
    private RadioButton rb_manhua_paihang;
    private RadioButton rb_manhua_fenlei;
    private RadioButton rb_manhua_sousuo;
    private ImageView iv_manhua_top_yuan;
    private float now;
    private int nowIndex=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fl_main_manhua,null);

        //初始化视图
        vp_manhua = (ViewPager)view.findViewById(R.id.vp_manhua);
        rg_manhua_top = (RadioGroup)view.findViewById(R.id.rg_manhua_top);
        rb_manhua_jingpin = (RadioButton)view.findViewById(R.id.rb_manhua_jingpin);
        rb_manhua_gengxin = (RadioButton)view.findViewById(R.id.rb_manhua_gengxin);
        rb_manhua_paihang = (RadioButton)view.findViewById(R.id.rb_manhua_paihang);
        rb_manhua_fenlei = (RadioButton)view.findViewById(R.id.rb_manhua_fenlei);
        rb_manhua_sousuo = (RadioButton)view.findViewById(R.id.rb_manhua_sousuo);
        iv_manhua_top_yuan = (ImageView)view.findViewById(R.id.iv_manhua_top_yuan);

        //设置View数据源
        fragmentList = new ArrayList<>();
        fragmentList.add(new FmManhuaJingpin());
        fragmentList.add(new FmManhuaGengxin());
        fragmentList.add(new FmManhuaPaihang());
        fragmentList.add(new FmManhuaFenlei());
        fragmentList.add(new FmManhuaSousuo());

        //设置适配器
        mpa = new ManhuaPagerAdapter(this.getChildFragmentManager(),fragmentList);
        vp_manhua.setAdapter(mpa);

        //设置监听事件
        rg_manhua_top.setOnCheckedChangeListener(this);
        vp_manhua.addOnPageChangeListener(this);

        //默认选项
        vp_manhua.setCurrentItem(0);
        rb_manhua_jingpin.setChecked(true);
        now = iv_manhua_top_yuan.getPivotX();

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        setAllTextSize();
        switch (checkedId){
            case R.id.rb_manhua_jingpin:
                rb_manhua_jingpin.setTextSize(20);
                vp_manhua.setCurrentItem(0);
                break;
            case R.id.rb_manhua_gengxin:
                rb_manhua_gengxin.setTextSize(20);
                vp_manhua.setCurrentItem(1);
                break;
            case R.id.rb_manhua_paihang:
                rb_manhua_paihang.setTextSize(20);
                vp_manhua.setCurrentItem(2);
                break;
            case R.id.rb_manhua_fenlei:
                rb_manhua_fenlei.setTextSize(20);
                vp_manhua.setCurrentItem(3);
                break;
            case R.id.rb_manhua_sousuo:
                rb_manhua_sousuo.setTextSize(20);
                vp_manhua.setCurrentItem(4);
                break;
        }
    }

    private void setAllTextSize() {
        rb_manhua_jingpin.setTextSize(17);
        rb_manhua_gengxin.setTextSize(17);
        rb_manhua_paihang.setTextSize(17);
        rb_manhua_fenlei.setTextSize(17);
        rb_manhua_sousuo.setTextSize(17);
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
                rb_manhua_jingpin.setChecked(true);
                nowIndex = 1;
                break;
            case 1:
                toMove(nowIndex,2,animation);
                rb_manhua_gengxin.setChecked(true);
                nowIndex = 2;
                break;
            case 2:
                toMove(nowIndex,3,animation);
                rb_manhua_paihang.setChecked(true);
                nowIndex = 3;
                break;
            case 3:
                toMove(nowIndex,4,animation);
                rb_manhua_fenlei.setChecked(true);
                nowIndex = 4;
                break;
            case 4:
                toMove(nowIndex,5,animation);
                rb_manhua_sousuo.setChecked(true);
                nowIndex = 5;
                break;
        }
    }

    private void toMove(int nowIndex, int to, Animation animation) {
        float toOne = rg_manhua_top.getWidth()/5;
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
            case 4:
                animation = new TranslateAnimation(now+toOne*(nowIndex-1),now+3*toOne,0,0);
                break;
            case 5:
                animation = new TranslateAnimation(now+toOne*(nowIndex-1),now+4*toOne,0,0);

                break;
        }
        animation.setDuration(100);
        animation.setFillAfter(true);
        iv_manhua_top_yuan.startAnimation(animation);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
