package com.administrator.youyaoqi.myapplication;

import android.app.Application;

import com.administrator.youyaoqi.Bean.GengxinItemData;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
public class MyApplication extends Application {

    private List<GengxinItemData> muluListT;

    public List<GengxinItemData> getMuluListT() {
        return muluListT;
    }

    public void setMuluListT(List<GengxinItemData> muluListT) {
        this.muluListT = muluListT;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
