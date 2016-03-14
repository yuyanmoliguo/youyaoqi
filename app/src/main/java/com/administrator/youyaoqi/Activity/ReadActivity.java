package com.administrator.youyaoqi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.youyaoqi.Bean.GengxinItemData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.myapplication.MyApplication;
import com.administrator.youyaoqi.pagerAdapter.ReadMainPagerAdapter;
import com.administrator.youyaoqi.utils.BitmapLoader;
import com.administrator.youyaoqi.utils.DownloadJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class ReadActivity extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager vp_read_main;
    private String readUrlNow;
    private String readNum;
    private ImageView iv_read_main;
    private List<View> viewList;
    private ReadMainPagerAdapter rmpa;
    private String pagernow;
    private String id;
    private int numFlag;//0为第一章第一页，2为最后一章最后一页，1为中间任意页
    private boolean flag;//false 为正常滑动，true为无法滑动
    private List<GengxinItemData> muluListT;
    private TextView tv_read_name1;
    private TextView tv_read_name2;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.read_activity_main);
        //获得全局变量
        MyApplication myApplication = (MyApplication) getApplication();
        muluListT = myApplication.getMuluListT();
        //Intent获取值
        Intent it = getIntent();
        readNum = it.getStringExtra("picNum");
        pagernow = it.getStringExtra("pagerNow");
        id = it.getStringExtra("id");
        name = it.getStringExtra("name");
        String modeFlag = it.getStringExtra("modeFlag");
        //得到当前Url
        readUrlNow = "http://app.u17.com/v3/app/android/phone/comic/chapter?chapter_id="
                +pagernow
                +"&t="
                +new Date().getTime()/1000
                +"&v=2300009&android_id=f5b788c14b0ac4f8&key=null&come_from=u17&model=HUAWEI+GRA-TL00";

        tv_read_name1 = (TextView)this.findViewById(R.id.tv_read_name1);
        tv_read_name2 = (TextView)this.findViewById(R.id.tv_read_name2);
        tv_read_name1.setText(name + ":");
        tv_read_name2.setText(muluListT.get(Integer.parseInt(id)).getName());

        //设置ViewPager
        vp_read_main = (ViewPager)this.findViewById(R.id.vp_read_main);
        viewList = new ArrayList<>();
        for (int i=0;i<Integer.parseInt(readNum);i++){
            View view = LayoutInflater.from(this).inflate(R.layout.vp_read_main,null);
            viewList.add(view);
        }
        rmpa = new ReadMainPagerAdapter(this,readUrlNow,viewList);
        vp_read_main.setAdapter(rmpa);

        //从前向后进入
        if (Integer.parseInt(modeFlag) == 1){
            //设置ViewPager默认页
            vp_read_main.setCurrentItem(0);
            //判断当前是否为第一章节，设置默认值
            isStart();
        }
        //从后向前进入
        if (Integer.parseInt(modeFlag) == 2){
            //设置ViewPager默认页
            vp_read_main.setCurrentItem(Integer.parseInt(readNum) - 1);
            //判断当前是否为最后一章，设置默认值
            isEnd();
        }

        //监听ViewPager翻页
        vp_read_main.addOnPageChangeListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("jinru", "3");
    }

    /**
     * 判断当前是否为最后一章
     */
    private void isEnd(){
        //判断当前是否为最后一章，设置默认值
        if (Integer.parseInt(id) == muluListT.size()-1){
            Log.i("num","最后一章最后一页");
            numFlag = 4;

        }
        //其他章
        if (Integer.parseInt(id) != muluListT.size()-1){
            Log.i("num","其他章最后一页");
            numFlag = 3;
        }
    }

    /**
     * 判断当前是否为第一章
     */
    private void isStart(){
        //第一章
        if (Integer.parseInt(id) == 0){
            Log.i("num","第一章第一页");
            numFlag = 1;
        }
        //其他章
        if (Integer.parseInt(id) != 0){
            Log.i("num","其他章第一页");
            numFlag = 2;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i("22", "position:" + position + "  " + "positionOffset:" + positionOffset + "  " + "positionOffsetPixels:" + positionOffsetPixels);
        //其他章的最后一页向后滑动
        if (numFlag == 3 && flag && positionOffsetPixels==0){
            toNext();
        }

        //其他章的第一页向前滑动
        if (numFlag == 2 && flag && positionOffsetPixels==0){
            toBefore();
        }

        //第一章第一页向前滑动
        if (numFlag == 1 && flag && positionOffsetPixels==0){
            Toast.makeText(ReadActivity.this, "当前为第一章", Toast.LENGTH_SHORT).show();
        }

        //最后一章最后一页向后滑动
        if (numFlag == 4 && flag && positionOffsetPixels==0) {
            Toast.makeText(ReadActivity.this, "未完待续", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 滑入前一个章节
     */
    private void toBefore() {
        Log.i("jinru","2");
        Intent it = new Intent(ReadActivity.this,ReadActivity.class);
//        it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        it.putExtra("pagerNow", muluListT.get(Integer.parseInt(id)-1).getId());
        it.putExtra("picNum", muluListT.get(Integer.parseInt(id)-1).getPicNum());
        it.putExtra("id",Integer.parseInt(id)-1+"");
        it.putExtra("name",name);
        it.putExtra("modeFlag", "2");
        startActivity(it);
//        overridePendingTransition(R.anim.activitys, R.anim.activitys);
//        ReadActivity.this.finish();
    }

    /**
     * 滑入后一个章节
     */
    private void toNext() {
        Log.i("jinru","1");
        Intent it = new Intent(ReadActivity.this,ReadActivity.class);
//        it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        it.putExtra("pagerNow", muluListT.get(Integer.parseInt(id)+1).getId());
        it.putExtra("picNum", muluListT.get(Integer.parseInt(id)+1).getPicNum());
        it.putExtra("id",Integer.parseInt(id)+1+"");
        it.putExtra("name",name);
        it.putExtra("modeFlag", "1");
        startActivity(it);
        ReadActivity.this.finish();
    }

    @Override
    public void onPageSelected(int position) {
        Log.i("22","position:"+position+"");

        //最后一页
        if (position==Integer.parseInt(readNum)-1){
            isEnd();
        }

        //第一页
        if (position==0){
           isStart();
        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i("22","state:"+state+"");
        if (state==1){
            flag = true;
        }else {
            flag = false;
        }

    }


}
