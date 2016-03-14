package com.administrator.youyaoqi.fragment;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.administrator.youyaoqi.Activity.LeixingItem;
import com.administrator.youyaoqi.Bean.ManhuaFeileiData;
import com.administrator.youyaoqi.Bean.ManhuaGengxinData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.constant.UrlConstants;
import com.administrator.youyaoqi.listAdapter.ManhuaFeileiAdapter;
import com.administrator.youyaoqi.utils.DownloadJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */
public class FmManhuaFenlei extends Fragment implements AdapterView.OnItemClickListener{

    private ManhuaFeileiAdapter mfa;
    private GridView gv_manhua_feilei_main;
    private List<ManhuaFeileiData.DataEntity.ReturnDataEntity.RankinglistEntity> feileiDatas;
    private ManhuaFeileiData mfd;
    private ImageView iv_fenlei_load;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_manhua_fenlei,null);

        gv_manhua_feilei_main = (GridView)view.findViewById(R.id.gv_manhua_feilei_main);
        iv_fenlei_load = (ImageView)view.findViewById(R.id.iv_fenlei_load2);
        feileiDatas = new ArrayList<>();
        //启动异步任务下载界面数据
        new GetFenleiDataTask().execute(UrlConstants.URL_MANHUA_FENLEIDATA);

        mfa = new ManhuaFeileiAdapter(getContext(),feileiDatas);
        gv_manhua_feilei_main.setAdapter(mfa);

        gv_manhua_feilei_main.setOnItemClickListener(this);

        iv_fenlei_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator oa = ObjectAnimator.ofFloat(iv_fenlei_load, "rotation", 0, 359);
                oa.setDuration(2000);
                oa.start();
                new GetFenleiDataTask().execute(UrlConstants.URL_MANHUA_FENLEIDATA);
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String argName = mfd.getData().getReturnData().getRankinglist().get(position).getArgName();
        String argValue = mfd.getData().getReturnData().getRankinglist().get(position).getArgValue();
        String argCon = mfd.getData().getReturnData().getRankinglist().get(position).getArgCon();
        String leixingItemUrl = "http://app.u17.com/v3/app/android/phone/list/index?size=40&page=1&argName="
                                +argName
                                +"&argValue="
                                +argValue
                                +"&con="
                                +argCon
                                +"&t=1457595476&v=2300009&android_id=f5b788c14b0ac4f8&key=null&come_from=u17&model=HUAWEI+GRA-TL00";
        Intent it = new Intent(getContext(),LeixingItem.class);
        it.putExtra("ItemUrl",leixingItemUrl);
        it.putExtra("ItemName",mfd.getData().getReturnData().getRankinglist().get(position).getSortName());
        startActivity(it);
    }

    class GetFenleiDataTask extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv_fenlei_load.setVisibility(View.VISIBLE);
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
                mfd = gson.fromJson(s,ManhuaFeileiData.class);
                if (mfd!=null){
                    feileiDatas.addAll(mfd.getData().getReturnData().getRankinglist());
                    mfa.notifyDataSetChanged();
                    iv_fenlei_load.setVisibility(View.GONE);
                }
            }
        }
    }

}
