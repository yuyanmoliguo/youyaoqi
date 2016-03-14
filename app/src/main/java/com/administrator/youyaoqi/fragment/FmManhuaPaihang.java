package com.administrator.youyaoqi.fragment;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.administrator.youyaoqi.Activity.PanghangItem;
import com.administrator.youyaoqi.Bean.ManhuaGengxinData;
import com.administrator.youyaoqi.Bean.ManhuaPaihangData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.constant.UrlConstants;
import com.administrator.youyaoqi.listAdapter.ManhuaPaihangAdapter;
import com.administrator.youyaoqi.utils.DownloadJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */
public class FmManhuaPaihang extends Fragment implements AdapterView.OnItemClickListener{

    private ListView lv_manhua_gengxin_main;
    private ManhuaPaihangAdapter mpa;
    private List<ManhuaPaihangData.DataEntity.ReturnDataEntity.RankinglistEntity> paihangDatas;
    private ManhuaPaihangData mpd;
    private ImageView iv_paihang_load;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_manhua_paihang,null);

        lv_manhua_gengxin_main = (ListView)view.findViewById(R.id.lv_manhua_paihang_main);
        iv_paihang_load = (ImageView)view.findViewById(R.id.iv_paihang_load);
        paihangDatas = new ArrayList<>();

        //启动异步任务下载界面数据
        new GetPaihangDataTask().execute(UrlConstants.URL_MANHUA_PAIHANGDATA);

        mpa = new ManhuaPaihangAdapter(getContext(),paihangDatas);

        lv_manhua_gengxin_main.setAdapter(mpa);

        lv_manhua_gengxin_main.setOnItemClickListener(this);

        iv_paihang_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator oa = ObjectAnimator.ofFloat(iv_paihang_load, "rotation", 0, 359);
                oa.setDuration(2000);
                oa.start();
                new GetPaihangDataTask().execute(UrlConstants.URL_MANHUA_PAIHANGDATA);
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it = new Intent(getContext(),PanghangItem.class);
        if (mpd!=null){
            String argName = mpd.getData().getReturnData().getRankinglist().get(position).getArgName();
            int argValue = mpd.getData().getReturnData().getRankinglist().get(position).getArgValue();
            String paihangItemUrl = "http://app.u17.com/v3/app/android/phone/list/index?size=40&page=1&argName="
                                    +argName
                                    +"&argValue="
                                    +argValue
                                    +"&con=0&t=1457579644&v=2300009&android_id=f5b788c14b0ac4f8&key=null&come_from=u17&model=HUAWEI+GRA-TL00";
            it.putExtra("ItemUrl",paihangItemUrl);
            it.putExtra("itemName",mpd.getData().getReturnData().getRankinglist().get(position).getRankingName());
        }
        startActivity(it);
    }


    class GetPaihangDataTask extends AsyncTask<String,Void,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv_paihang_load.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            return DownloadJson.httpGet(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s!=null){
                Gson gson = new Gson();
//            解析Json数组，中括号数组，大括号对象
//            Type typeManHuaPaihang = new TypeToken<List<ManhuaPaihangData>>() {
//            }.getType();
                mpd = gson.fromJson(s,ManhuaPaihangData.class);
                if (mpd!=null){
                    paihangDatas.addAll(mpd.getData().getReturnData().getRankinglist());
                    mpa.notifyDataSetChanged();
                    iv_paihang_load.setVisibility(View.GONE);
                }
            }
        }
    }

}
