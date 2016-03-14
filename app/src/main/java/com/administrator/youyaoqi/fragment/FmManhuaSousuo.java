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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.administrator.youyaoqi.Activity.SousuoMainActivity;
import com.administrator.youyaoqi.Bean.ManhuaFeileiData;
import com.administrator.youyaoqi.Bean.ManhuaSousuoDATA;
import com.administrator.youyaoqi.R;
import com.administrator.youyaoqi.constant.UrlConstants;
import com.administrator.youyaoqi.listAdapter.ManhuaSousuoAdapter;
import com.administrator.youyaoqi.utils.DownloadJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */
public class FmManhuaSousuo extends Fragment implements AdapterView.OnItemClickListener{

    private GridView gv_manhua_sousuo_main;

    private ManhuaSousuoAdapter msa;
    private List<ManhuaSousuoDATA.DataEntity.ReturnDataEntity> sousuoDatas;
    private Button bt_manhua_sousuo_go;
    private ImageView iv_sousuo_load;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_manhuan_sousuo,null);

        gv_manhua_sousuo_main = (GridView)view.findViewById(R.id.gv_manhua_sousuo_main);
        iv_sousuo_load = (ImageView)view.findViewById(R.id.iv_sousuo_load);
        sousuoDatas = new ArrayList<>();
        //启动异步任务下载界面数据
        new GetSousuoDataTask().execute(UrlConstants.URL_MANHUA_SOUSUODATA);
        //设置适配器
        msa = new ManhuaSousuoAdapter(getContext(),sousuoDatas);
        gv_manhua_sousuo_main.setAdapter(msa);
        //监听跳转按钮
        bt_manhua_sousuo_go = (Button)view.findViewById(R.id.bt_manhua_sousuo_go);
        bt_manhua_sousuo_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(),SousuoMainActivity.class);
                startActivity(it);
            }
        });
        gv_manhua_sousuo_main.setOnItemClickListener(this);

        iv_sousuo_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator oa = ObjectAnimator.ofFloat(iv_sousuo_load, "rotation", 0, 359);
                oa.setDuration(2000);
                oa.start();
                new GetSousuoDataTask().execute(UrlConstants.URL_MANHUA_SOUSUODATA);
            }
        });

        return view;
    }

    /**
     * 点击搜索指定漫画
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it = new Intent(getContext(),SousuoMainActivity.class);
        String cont = sousuoDatas.get(position).getTag();
        it.putExtra("tag",cont);
        startActivity(it);
    }

    /**
     * 异步下载并解析Json
     */
    class GetSousuoDataTask extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv_sousuo_load.setVisibility(View.VISIBLE);
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
                ManhuaSousuoDATA msd = gson.fromJson(s,ManhuaSousuoDATA.class);
                if (msd!=null){
                    sousuoDatas.addAll(msd.getData().getReturnData());
                    msa.notifyDataSetChanged();
                    iv_sousuo_load.setVisibility(View.GONE);
                }
            }
        }
    }

}
