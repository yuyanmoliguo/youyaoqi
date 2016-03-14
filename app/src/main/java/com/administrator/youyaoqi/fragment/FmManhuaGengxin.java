package com.administrator.youyaoqi.fragment;


import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.administrator.youyaoqi.Activity.GengxinItem;
import com.administrator.youyaoqi.Bean.ManhuaGengxinData;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.constant.UrlConstants;
import com.administrator.youyaoqi.listAdapter.ManhuaGengxinAdapter;
import com.administrator.youyaoqi.utils.DownloadJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Administrator on 2016/3/5.
 */
public class FmManhuaGengxin extends Fragment  implements AdapterView.OnItemClickListener {

    private ListView lv_manhua_gengxin_main;
    private ManhuaGengxinAdapter mga;
    private List<ManhuaGengxinData.DataEntity.ReturnDataEntity> gengxinDatas;
    private ImageView iv_gengxin_load;
//    private SwipyRefreshLayout sw_manhua_gengxin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_manhua_gengxin,null);

        //初始化视图
        iv_gengxin_load = (ImageView)view.findViewById(R.id.iv_gengxin_load);
        lv_manhua_gengxin_main = (ListView)view.findViewById(R.id.lv_manhua_gengxin_main);
//        sw_manhua_gengxin = (SwipyRefreshLayout)view.findViewById(R.id.sw_manhua_gengxin);
        //设置数据源
        gengxinDatas = new ArrayList<>();
        //启动异步任务下载界面数据
        new GetGengxinDataTask().execute(UrlConstants.URL_MANHUA_GENGXINDATA);
        //设置适配器
        mga = new ManhuaGengxinAdapter(getContext(),gengxinDatas);
        lv_manhua_gengxin_main.setAdapter(mga);

        lv_manhua_gengxin_main.setOnItemClickListener(this);
//        sw_manhua_gengxin.setDirection(SwipyRefreshLayoutDirection.TOP);
//        sw_manhua_gengxin.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh(SwipyRefreshLayoutDirection direction) {
//                sw_manhua_gengxin.setRefreshing(false);
//                new GetGengxinDataTask().execute(UrlConstants.URL_MANHUA_GENGXINDATA);
//            }
//        });

        iv_gengxin_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator oa = ObjectAnimator.ofFloat(iv_gengxin_load, "rotation", 0, 359);
                oa.setDuration(2000);
                oa.start();
                new GetGengxinDataTask().execute(UrlConstants.URL_MANHUA_GENGXINDATA);
            }
        });

        return view;
    }

    /**
     * 列表点击跳转
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String comic_id = String.valueOf(gengxinDatas.get(position).getComic_id());
        String last_update_time = String.valueOf(gengxinDatas.get(position).getLast_update_time());
        String itemUrl = "http://app.u17.com/v3/app/android/phone/comic/detail_static?comicid="
                        +comic_id
                        +"&t="
                        +last_update_time
                        +"&v=2300009&android_id=f5b788c14b0ac4f8&key=null&come_from=u17&model=HUAWEI+GRA-TL00";

        Intent it = new Intent(getContext(), GengxinItem.class);
        it.putExtra("itemUrl",itemUrl);
        startActivity(it);

    }

    class GetGengxinDataTask extends AsyncTask<String,Void,String>{
        private Animation animation;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv_gengxin_load.setVisibility(View.VISIBLE);
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
                ManhuaGengxinData mgd = gson.fromJson(s,ManhuaGengxinData.class);
                if (mgd!=null){
                    gengxinDatas.addAll(mgd.getData().getReturnData());
                    mga.notifyDataSetChanged();
                    iv_gengxin_load.setVisibility(View.GONE);
                }
            }
        }
    }
}
