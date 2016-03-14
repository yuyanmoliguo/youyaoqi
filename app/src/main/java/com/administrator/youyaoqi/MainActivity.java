package com.administrator.youyaoqi;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.administrator.youyaoqi.fragment.FlFaxian;
import com.administrator.youyaoqi.fragment.FlManhua;
import com.administrator.youyaoqi.fragment.FlShoucang;
import com.administrator.youyaoqi.fragment.FlWode;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{


    private RadioGroup rg_main_bottom;
    private RadioButton rb_main_manhua;
    private FragmentManager manager;
    private FlManhua flManhua;
    private FlShoucang flShoucang;
    private FlFaxian flFaxian;
    private FlWode flWode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        SharedPreferences sp_save = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp_save.edit();
        editor.putInt("num", 2);
        editor.commit();

        setContentView(R.layout.activity_main);
        //初始化
        rg_main_bottom = (RadioGroup) this.findViewById(R.id.rg_main_bottom);
        rb_main_manhua = (RadioButton)this.findViewById(R.id.rb_main_manhua);
        manager = this.getSupportFragmentManager();

        //单选监听事件
        rg_main_bottom.setOnCheckedChangeListener(this);
        //设置初始选项
        rb_main_manhua.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        FragmentTransaction transaction = manager.beginTransaction();

        hideAll(transaction);

        switch (checkedId){
            case R.id.rb_main_manhua:
                if (flManhua==null){
                    flManhua = new FlManhua();
                    transaction.add(R.id.fl_main,flManhua);
                }else {
                    transaction.show(flManhua);
                }
                break;
            case R.id.rb_main_shoucang:
                if (flShoucang==null){
                    flShoucang = new FlShoucang();
                    transaction.add(R.id.fl_main,flShoucang);
                }else {
                    transaction.show(flShoucang);
                }
                break;
            case R.id.rb_main_faxian:
                if (flFaxian==null){
                    flFaxian = new FlFaxian();
                    transaction.add(R.id.fl_main,flFaxian);
                }else {
                    transaction.show(flFaxian);
                }
                break;
            case R.id.rb_main_wode:
                if (flWode==null){
                    flWode = new FlWode();
                    transaction.add(R.id.fl_main,flWode);
                }else {
                    transaction.show(flWode);
                }
                break;
        }
        transaction.commit();
    }

    private void hideAll(FragmentTransaction transaction) {
        if (flManhua!=null){
            transaction.hide(flManhua);
        }
        if (flShoucang!=null){
            transaction.hide(flShoucang);
        }
        if (flFaxian!=null){
            transaction.hide(flFaxian);
        }
        if (flWode!=null){
            transaction.hide(flWode);
        }
    }
}
