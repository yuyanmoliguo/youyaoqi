package com.administrator.youyaoqi.Activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.administrator.youyaoqi.Bean.GengxinItemData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.fragment.GengxinItemFragment;
import com.administrator.youyaoqi.listAdapter.GengxinMuluAdapter;
import com.administrator.youyaoqi.utils.BitmapLoader;
import com.administrator.youyaoqi.utils.DownloadJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class GengxinItem extends FragmentActivity{

    private Fragment gengxinItem;
    private android.support.v4.app.FragmentManager manager;
    private String itemUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gengxin_item);

        //接收上个界面的URL
        Intent it = getIntent();
        itemUrl = it.getStringExtra("itemUrl");

        gengxinItem = new GengxinItemFragment(itemUrl);
        manager = this.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_gengxin_item,gengxinItem);
        transaction.commit();

    }
}
