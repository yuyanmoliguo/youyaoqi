package com.administrator.youyaoqi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.administrator.youyaoqi.MainActivity;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.pagerAdapter.WelcomePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
public class WelcomeActivity extends Activity implements ViewPager.OnPageChangeListener{


    private RadioGroup rg_welcome;
    private RadioButton rb_welcome1;
    private List<View> viewList;
    private ViewPager vp_welcome;
    private RadioButton rb_welcome2;
    private RadioButton rb_welcome3;
    private Button bt_welcome;
    private WelcomePagerAdapter wpa;
    private ImageView iv_login3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        SharedPreferences sp_read = getSharedPreferences("login", MODE_PRIVATE);
        int num = sp_read.getInt("num", 1);
        if (num == 1){
            setContentView(R.layout.activity_welcome);
            vp_welcome = (ViewPager) this.findViewById(R.id.vp_welcome);
            rg_welcome = (RadioGroup)this.findViewById(R.id.rg_welcome);
            rb_welcome1 = (RadioButton)this.findViewById(R.id.rb_welcome1);
            rb_welcome2 = (RadioButton)this.findViewById(R.id.rb_welcome2);
            rb_welcome3 = (RadioButton)this.findViewById(R.id.rb_welcome3);
            viewList = new ArrayList<>();
            View view1 = LayoutInflater.from(this).inflate(R.layout.vp_welcome1,null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.vp_welcome2,null);
            View view3 = LayoutInflater.from(this).inflate(R.layout.vp_welcome3,null);
            bt_welcome = (Button) view3.findViewById(R.id.bt_welcome);
            viewList.add(view1);
            viewList.add(view2);
            viewList.add(view3);

            vp_welcome.setCurrentItem(0);
            rb_welcome1.setChecked(true);

            vp_welcome.addOnPageChangeListener(this);

            wpa = new WelcomePagerAdapter(viewList);
            vp_welcome.setAdapter(wpa);

            bt_welcome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(it);
                    WelcomeActivity.this.finish();
                }
            });

        } else {
            setContentView(R.layout.activity_login3);
            new Welcome3().execute();
        }

    }


    class Welcome3 extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent it = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(it);
            WelcomeActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(it);
        WelcomeActivity.this.finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rb_welcome1.setChecked(true);
                break;
            case 1:
                rb_welcome2.setChecked(true);
                break;
            case 2:
                rb_welcome3.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
