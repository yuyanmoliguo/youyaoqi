package com.administrator.youyaoqi.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.pagerAdapter.JingpinTopAdapter;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/3/5.
 */
public class FmManhuaJingpin extends Fragment implements ViewPager.OnPageChangeListener{
    private RadioGroup rg_manhua_jingpin_top;
    private RadioButton rb_manhua_jingpin_top1;
    private RadioButton rb_manhua_jingpin_top2;
    private RadioButton rb_manhua_jingpin_top3;
    private RadioButton rb_manhua_jingpin_top4;
    private ViewPager vp_manhua_jingpin_top;
    private List<View> viewList;
    private JingpinTopAdapter jta;
    private Button bt_manhua_jingpin_s1;
    private Timer timer;
    private SwipyRefreshLayout sw_manhua_jingpin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //父界面
        View view = inflater.inflate(R.layout.fm_manhua_jingpin,null);
        //ViewPager
        initPager(inflater, view);
        sw_manhua_jingpin.setDirection(SwipyRefreshLayoutDirection.TOP);
        sw_manhua_jingpin.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                sw_manhua_jingpin.setRefreshing(false);
            }
        });



//        bt_manhua_jingpin_s1 = (Button)view.findViewById(R.id.bt_manhua_jingpin_s1);
//        Drawable picS1 = getResources().getDrawable(R.mipmap.titlebar_bg);
//        picS1.setBounds(0, 0, picS1.getMinimumWidth(), picS1.getMinimumHeight());
//        bt_manhua_jingpin_s1.setCompoundDrawables(null,picS1,null,null);
//        bt_manhua_jingpin_s1.setText("第一个");


        return view;
    }

    /**
     * 创建顶部滑动ViewPager
     * @param inflater
     * @param view
     */
    private void initPager(LayoutInflater inflater, View view) {
        //初始化视图
        rg_manhua_jingpin_top = (RadioGroup)view.findViewById(R.id.rg_manhua_jingpin_top);
        rb_manhua_jingpin_top1 = (RadioButton)view.findViewById(R.id.rb_manhua_jingpin_top1);
        rb_manhua_jingpin_top2 = (RadioButton)view.findViewById(R.id.rb_manhua_jingpin_top2);
        rb_manhua_jingpin_top3 = (RadioButton)view.findViewById(R.id.rb_manhua_jingpin_top3);
        rb_manhua_jingpin_top4 = (RadioButton)view.findViewById(R.id.rb_manhua_jingpin_top4);
        vp_manhua_jingpin_top = (ViewPager)view.findViewById(R.id.vp_manhua_jingpin_top);
        sw_manhua_jingpin = (SwipyRefreshLayout)view.findViewById(R.id.sw_manhua_jingpin);

        //设置ViewPager数据源
        viewList = new ArrayList<>();
        viewList.add(inflater.inflate(R.layout.vp_manhua_jingpin_top,null));
        viewList.add(inflater.inflate(R.layout.vp_manhua_jingpin_top, null));
        viewList.add(inflater.inflate(R.layout.vp_manhua_jingpin_top,null));
        viewList.add(inflater.inflate(R.layout.vp_manhua_jingpin_top, null));

        //设置ViewPager适配器
        jta = new JingpinTopAdapter(viewList);
        vp_manhua_jingpin_top.setAdapter(jta);

        //设置监听
        vp_manhua_jingpin_top.addOnPageChangeListener(this);

        //默认选项
        rb_manhua_jingpin_top1.setChecked(true);
        vp_manhua_jingpin_top.setCurrentItem(Integer.MAX_VALUE / 2 + 1);

        //创建线程设置ViewPager自动跳转
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                hd.sendEmptyMessage(0x123);
            }
        }, 4000, 4000);
    }

    /**
     * 暂停时结束线程跳转图片
     */
    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }


    private Handler hd  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x123){
                int nowItem = vp_manhua_jingpin_top.getCurrentItem();
                vp_manhua_jingpin_top.setCurrentItem(nowItem+1);
            }
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position%4){
            case 0:
                rb_manhua_jingpin_top1.setChecked(true);
                break;
            case 1:
                rb_manhua_jingpin_top2.setChecked(true);
                break;
            case 2:
                rb_manhua_jingpin_top3.setChecked(true);
                break;
            case 3:
                rb_manhua_jingpin_top4.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
